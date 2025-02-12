/*
 * Copyright 2024, Redis Ltd. and Contributors
 * All rights reserved.
 *
 * Licensed under the MIT License.
 */

package io.lettuce.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class RedisContainerIntegrationTests {

    private static final Logger LOGGER = LogManager.getLogger(RedisContainerIntegrationTests.class);

    private static final String REDIS_STACK_STANDALONE = "standalone-stack";

    private static final String REDIS_STACK_CLUSTER = "clustered-stack";

    private static final String REDIS_STACK_VERSION = System.getProperty("REDIS_STACK_VERSION");

    private static Exception initializationException;

    public static ComposeContainer CLUSTERED_STACK = new ComposeContainer(
            new File("src/test/resources/docker/docker-compose.yml")).withExposedService(REDIS_STACK_CLUSTER, 36379)
                    .withExposedService(REDIS_STACK_CLUSTER, 36380).withExposedService(REDIS_STACK_CLUSTER, 36381)
                    .withExposedService(REDIS_STACK_STANDALONE, 6379).withPull(false).withLocalCompose(true);

    // Singleton container pattern - start the containers only once
    // See https://java.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers
    static {
        int attempts = 0;

        if (REDIS_STACK_VERSION != null && !REDIS_STACK_VERSION.isEmpty()) {
            CLUSTERED_STACK.withEnv("REDIS_VERSION", REDIS_STACK_VERSION);
        }
        // In case you need to debug the container uncomment these lines to redirect the output
        CLUSTERED_STACK.withLogConsumer(REDIS_STACK_CLUSTER, (OutputFrame frame) -> LOGGER.debug(frame.getUtf8String()));
        CLUSTERED_STACK.withLogConsumer(REDIS_STACK_STANDALONE, (OutputFrame frame) -> LOGGER.debug(frame.getUtf8String()));

        CLUSTERED_STACK.waitingFor(REDIS_STACK_CLUSTER, Wait.forLogMessage("*Starting redis instance.*", 1));
        do {
            try {
                CLUSTERED_STACK.start();
            } catch (Exception e) {
                initializationException = e;
            }
            // Attempt to stabilize the pipeline - sometime the `docker compose up` fails randomly
        } while (initializationException != null && attempts++ < 3);
    }

    @BeforeAll
    public static void checkContainerInitialization() {
        if (initializationException != null) {
            throw new IllegalStateException("Failed to initialize containers", initializationException);
        }
    }

}
