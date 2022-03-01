/*
 * Copyright 2020-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.lettuce.core.api.coroutines

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import io.lettuce.core.KillArgs
import io.lettuce.core.TrackingArgs
import io.lettuce.core.UnblockType

/**
 * Coroutine executed commands for connection commands.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @since 7.0
 * @generated by io.lettuce.apigenerator.CreateKotlinCoroutinesApi
 */
@ExperimentalLettuceCoroutinesApi
interface RedisConnectionCoroutinesCommands<K : Any, V : Any> {

    /**
     * Authenticate to the server.
     *
     * @param password the password
     * @return String simple-string-reply
     */
    suspend fun auth(password: CharSequence): String?

    /**
     * Authenticate to the server with username and password. Requires Redis 6 or newer.
     *
     * @param username the username
     * @param password the password
     * @return String simple-string-reply
     * @since 6.0
     */
    suspend fun auth(username: String, password: CharSequence): String?

    /**
     * Control tracking of keys in the context of server-assisted client cache invalidation.
     *
     * @param enabled @code true} to enable key tracking.
     * @return String simple-string-reply `OK`.
     * @since 6.0
     */
    suspend fun clientCaching(enabled: Boolean): String?

    /**
     * Get the current connection name.
     *
     * @return K bulk-string-reply The connection name, or a null bulk reply if no name is set.
     */
    suspend fun clientGetname(): K?

    /**
     * Returns the client ID we are redirecting our tracking notifications to.
     *
     * @return the ID of the client we are redirecting the notifications to. The command returns -1 if client tracking is not
     *         enabled, or 0 if client tracking is enabled but we are not redirecting the notifications to any client.
     * @since 6.0
     */
    suspend fun clientGetredir(): Long?

    /**
     * Get the id of the current connection.
     *
     * @return Long The command just returns the ID of the current connection.
     * @since 5.3
     */
    suspend fun clientId(): Long?

    /**
     * Kill the connection of a client identified by ip:port.
     *
     * @param addr ip:port.
     * @return String simple-string-reply `OK` if the connection exists and has been closed.
     */
    suspend fun clientKill(addr: String): String?

    /**
     * Kill connections of clients which are filtered by `killArgs`.
     *
     * @param killArgs args for the kill operation.
     * @return Long integer-reply number of killed connections.
     */
    suspend fun clientKill(killArgs: KillArgs): Long?

    /**
     * Get the list of client connections.
     *
     * @return String bulk-string-reply a unique string, formatted as follows: One client connection per line (separated by LF),
     *         each line is composed of a succession of property=value fields separated by a space character.
     */
    suspend fun clientList(): String?

    /**
     * Stop processing commands from clients for some time.
     *
     * @param timeout the timeout value in milliseconds.
     * @return String simple-string-reply The command returns OK or an error if the timeout is invalid.
     */
    suspend fun clientPause(timeout: Long): String?

    /**
     * Set the current connection name.
     *
     * @param name the client name.
     * @return simple-string-reply `OK` if the connection name was successfully set.
     */
    suspend fun clientSetname(name: K): String?

    /**
     * Enables the tracking feature of the Redis server, that is used for server assisted client side caching. Tracking messages
     * are either available when using the RESP3 protocol or through Pub/Sub notification when using RESP2.
     *
     * @param args for the CLIENT TRACKING operation.
     * @return String simple-string-reply `OK`.
     * @since 6.0
     */
    suspend fun clientTracking(args: TrackingArgs): String?

    /**
     * Unblock the specified blocked client.
     *
     * @param id the client id.
     * @param type unblock type.
     * @return Long integer-reply number of unblocked connections.
     * @since 5.1
     */
    suspend fun clientUnblock(id: Long, type: UnblockType): Long?

    /**
     * Echo the given string.
     *
     * @param msg the message type: value.
     * @return V bulk-string-reply.
     */
    suspend fun echo(msg: V): V

    /**
     * Return the role of the instance in the context of replication.
     *
     * @return List<Any> array-reply where the first element is one of master, slave, sentinel and the additional
     *         elements are role-specific.
     */
    suspend fun role(): List<Any>

    /**
     * Ping the server.
     *
     * @return String simple-string-reply.
     */
    suspend fun ping(): String

    /**
     * Switch connection to Read-Only mode when connecting to a cluster.
     *
     * @return String simple-string-reply.
     */
    suspend fun readOnly(): String

    /**
     * Switch connection to Read-Write mode (default) when connecting to a cluster.
     *
     * @return String simple-string-reply.
     */
    suspend fun readWrite(): String

    /**
     * Instructs Redis to disconnect the connection. Note that if auto-reconnect is enabled then Lettuce will auto-reconnect if
     * the connection was disconnected. Use [io.lettuce.core.api.StatefulConnection#close] to close connections and
     * release resources.
     *
     * @return String simple-string-reply always OK.
     */
    suspend fun quit(): String?

}
