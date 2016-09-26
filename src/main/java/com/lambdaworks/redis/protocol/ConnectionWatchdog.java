// Copyright (C) 2011 - Will Glozer.  All rights reserved.

package com.lambdaworks.redis.protocol;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.lambdaworks.redis.ClientOptions;
import com.lambdaworks.redis.ConnectionEvents;
import com.lambdaworks.redis.RedisChannelHandler;
import com.lambdaworks.redis.internal.LettuceAssert;

import com.lambdaworks.redis.resource.Delay;
import com.lambdaworks.redis.resource.Delay.StatefulDelay;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.internal.logging.InternalLogLevel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * A netty {@link ChannelHandler} responsible for monitoring the channel and reconnecting when the connection is lost.
 * 
 * @author Will Glozer
 * @author Mark Paluch
 */
@ChannelHandler.Sharable
public class ConnectionWatchdog extends ChannelInboundHandlerAdapter implements TimerTask {

    public static final long LOGGING_QUIET_TIME_MS = TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS);

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ConnectionWatchdog.class);

    private final Delay reconnectDelay;
    private final Bootstrap bootstrap;
    private final EventExecutorGroup reconnectWorkers;
    private final ReconnectionHandler reconnectionHandler;
    private final ReconnectionListener reconnectionListener;

    private Channel channel;
    private final Timer timer;

    private SocketAddress remoteAddress;
    private long lastReconnectionLogging = -1;
    private CommandHandler<?, ?> commandHandler;

    private volatile int attempts;
    private volatile boolean listenOnChannelInactive;
    private volatile Timeout reconnectScheduleTimeout;
    private volatile String logPrefix;

    /**
     * Create a new watchdog that adds to new connections to the supplied {@link ChannelGroup} and establishes a new
     * {@link Channel} when disconnected, while reconnect is true. The socketAddressSupplier can supply the reconnect address.
     * 
     * @param reconnectDelay reconnect delay, must not be {@literal null}
     * @param clientOptions client options for the current connection, must not be {@literal null}
     * @param bootstrap Configuration for new channels, must not be {@literal null}
     * @param timer Timer used for delayed reconnect, must not be {@literal null}
     * @param reconnectWorkers executor group for reconnect tasks, must not be {@literal null}
     * @param socketAddressSupplier the socket address supplier to obtain an address for reconnection, may be {@literal null}
     * @param reconnectionListener the reconnection listener, must not be {@literal null}
     */
    public ConnectionWatchdog(Delay reconnectDelay, ClientOptions clientOptions, Bootstrap bootstrap, Timer timer,
            EventExecutorGroup reconnectWorkers, Supplier<SocketAddress> socketAddressSupplier,
            ReconnectionListener reconnectionListener) {

        LettuceAssert.notNull(reconnectDelay, "Delay must not be null");
        LettuceAssert.notNull(clientOptions, "ClientOptions must not be null");
        LettuceAssert.notNull(bootstrap, "Bootstrap must not be null");
        LettuceAssert.notNull(timer, "Timer must not be null");
        LettuceAssert.notNull(reconnectWorkers, "ReconnectWorkers must not be null");
        LettuceAssert.notNull(reconnectionListener, "ReconnectionListener must not be null");

        this.reconnectDelay = reconnectDelay;
        resetReconnectDelay();
        this.bootstrap = bootstrap;
        this.timer = timer;
        this.reconnectWorkers = reconnectWorkers;
        this.reconnectionListener = reconnectionListener;
        Supplier<SocketAddress> wrappedSocketAddressSupplier = new Supplier<SocketAddress>() {
            @Override
            public SocketAddress get() {

                if (socketAddressSupplier != null) {
                    try {
                        remoteAddress = socketAddressSupplier.get();
                    } catch (RuntimeException e) {
                        logger.warn("Cannot retrieve the current address from socketAddressSupplier: " + e.toString()
                                + ", reusing old address " + remoteAddress);
                    }
                }

                return remoteAddress;
            }
        };

        this.reconnectionHandler = new ReconnectionHandler(clientOptions, bootstrap, wrappedSocketAddressSupplier);
    }

    private void resetReconnectDelay() {
        if (reconnectDelay instanceof StatefulDelay) {
            ((StatefulDelay) reconnectDelay).reset();
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        logger.debug("{} userEventTriggered({}, {})", logPrefix(), ctx, evt);

        if (evt instanceof ConnectionEvents.PrepareClose) {

            ConnectionEvents.PrepareClose prepareClose = (ConnectionEvents.PrepareClose) evt;
            prepareClose(prepareClose);
        }

        if (evt instanceof ConnectionEvents.Activated) {
            attempts = 0;
        }

        super.userEventTriggered(ctx, evt);
    }

    void prepareClose(ConnectionEvents.PrepareClose prepareClose) {

        setListenOnChannelInactive(false);
        setReconnectSuspended(true);
        prepareClose.getPrepareCloseFuture().complete(true);

        reconnectionHandler.prepareClose();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        if(commandHandler == null) {
            this.commandHandler = ctx.pipeline().get(CommandHandler.class);
        }

        reconnectScheduleTimeout = null;
        channel = ctx.channel();
        logger.debug("{} channelActive({})", logPrefix(), ctx);
        remoteAddress = channel.remoteAddress();
        resetReconnectDelay();

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        logger.debug("{} channelInactive({})", logPrefix(), ctx);
        channel = null;


        if (listenOnChannelInactive && !reconnectionHandler.isReconnectSuspended()) {
            RedisChannelHandler<?, ?> channelHandler = ctx.pipeline().get(RedisChannelHandler.class);
            if (channelHandler != null) {
                reconnectionHandler.setTimeout(channelHandler.getTimeout());
                reconnectionHandler.setTimeoutUnit(channelHandler.getTimeoutUnit());
            }

            scheduleReconnect();
        } else {
            logger.debug("{} Reconnect scheduling disabled", logPrefix(), ctx);
        }

        super.channelInactive(ctx);
    }

    /**
     * Schedule reconnect if channel is not available/not active.
     */
    public synchronized void scheduleReconnect() {

        logger.debug("{} scheduleReconnect()", logPrefix());

        if (!isEventLoopGroupActive()) {
            logger.debug("isEventLoopGroupActive() == false");
            return;
        }

        if (commandHandler != null && commandHandler.isClosed()) {
            logger.debug("Skip reconnect scheduling, CommandHandler is closed");
            return;
        }

        if ((channel == null || !channel.isActive()) && reconnectScheduleTimeout == null) {
            attempts++;

            final int attempt = attempts;
            int timeout = (int) reconnectDelay.getTimeUnit().toMillis(reconnectDelay.createDelay(attempt));
            logger.debug("Reconnect attempt {}, delay {}ms", attempt, timeout);
            this.reconnectScheduleTimeout = timer.newTimeout(new TimerTask() {
                @Override
                public void run(final Timeout timeout) throws Exception {

                    if (!isEventLoopGroupActive()) {
                        logger.debug("isEventLoopGroupActive() == false");
                        return;
                    }

                    reconnectWorkers.submit(() -> {
                        ConnectionWatchdog.this.run(timeout);
                        return null;
                    });
                }
            }, timeout, TimeUnit.MILLISECONDS);
        } else {
            logger.debug("{} Skipping scheduleReconnect() because I have an active channel", logPrefix());
        }
    }

    /**
     * Reconnect to the remote address that the closed channel was connected to. This creates a new {@link ChannelPipeline} with
     * the same handler instances contained in the old channel's pipeline.
     * 
     * @param timeout Timer task handle.
     *
     * @throws Exception when reconnection fails.
     */
    @Override
    public void run(Timeout timeout) throws Exception {

        reconnectScheduleTimeout = null;

        if (!isEventLoopGroupActive()) {
            logger.debug("isEventLoopGroupActive() == false");
            return;
        }

        if (commandHandler != null && commandHandler.isClosed()) {
            logger.debug("Skip reconnect scheduling, CommandHandler is closed");
            return;
        }

        boolean shouldLog = shouldLog();

        InternalLogLevel infoLevel = InternalLogLevel.INFO;
        InternalLogLevel warnLevel = InternalLogLevel.WARN;

        if (shouldLog) {
            lastReconnectionLogging = System.currentTimeMillis();
        } else {
            warnLevel = InternalLogLevel.DEBUG;
            infoLevel = InternalLogLevel.DEBUG;
        }

        try {
            reconnectionListener.onReconnect(new ConnectionEvents.Reconnect(attempts));
            reconnect(infoLevel, warnLevel);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        } catch (Exception e) {
            logger.log(warnLevel, "Cannot connect: {}", e.toString());
            if (!isReconnectSuspended()) {
                scheduleReconnect();
            }
        }
    }

    protected void reconnect(InternalLogLevel infoLevel, InternalLogLevel warnLevel) throws Exception {

        logger.log(infoLevel, "Reconnecting, last destination was {}", remoteAddress);
        reconnectionHandler.reconnect(infoLevel);
    }

    private boolean isEventLoopGroupActive() {

        if (!isEventLoopGroupActive(bootstrap.group()) || !isEventLoopGroupActive(reconnectWorkers)) {
            return false;
        }

        return true;
    }

    private boolean isEventLoopGroupActive(EventExecutorGroup executorService) {

        if (executorService.isShutdown() || executorService.isTerminated() || executorService.isShuttingDown()) {
            return false;
        }

        return true;
    }

    private boolean shouldLog() {

        long quietUntil = lastReconnectionLogging + LOGGING_QUIET_TIME_MS;

        if (quietUntil > System.currentTimeMillis()) {
            return false;
        }

        return true;
    }

    public void setListenOnChannelInactive(boolean listenOnChannelInactive) {
        this.listenOnChannelInactive = listenOnChannelInactive;
    }

    public boolean isListenOnChannelInactive() {
        return listenOnChannelInactive;
    }

    public boolean isReconnectSuspended() {
        return reconnectionHandler.isReconnectSuspended();
    }

    public void setReconnectSuspended(boolean reconnectSuspended) {
        reconnectionHandler.setReconnectSuspended(true);
    }

    ReconnectionHandler getReconnectionHandler() {
        return reconnectionHandler;
    }

    private String logPrefix() {

        if (logPrefix != null) {
            return logPrefix;
        }

        StringBuilder buffer = new StringBuilder(64);
        buffer.append('[').append("Last known addr=").append(remoteAddress).append(", ")
                .append(ChannelLogDescriptor.logDescriptor(channel)).append(']');
        return logPrefix = buffer.toString();
    }
}
