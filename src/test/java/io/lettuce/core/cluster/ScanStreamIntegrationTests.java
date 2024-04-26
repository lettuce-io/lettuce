package io.lettuce.core.cluster;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import reactor.test.StepVerifier;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanStream;
import io.lettuce.core.TestSupport;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.reactive.RedisAdvancedClusterReactiveCommands;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;
import io.lettuce.test.LettuceExtension;

/**
 * @author Mark Paluch
 */
@ExtendWith(LettuceExtension.class)
class ScanStreamIntegrationTests extends TestSupport {

    private final StatefulRedisClusterConnection<String, String> connection;

    private final RedisClusterCommands<String, String> redis;

    @Inject
    ScanStreamIntegrationTests(StatefulRedisClusterConnection<String, String> connection) {
        this.connection = connection;
        this.redis = connection.sync();
        this.redis.flushall();
    }

    @Test
    void shouldScanIteratively() {

        for (int i = 0; i < 1000; i++) {
            redis.set("key-" + i, value);
        }

        RedisAdvancedClusterReactiveCommands<String, String> reactive = connection.reactive();

        StepVerifier.create(ScanStream.scan(reactive, ScanArgs.Builder.limit(200)).take(250)).expectNextCount(250)
                .verifyComplete();
        StepVerifier.create(ScanStream.scan(reactive)).expectNextCount(1000).verifyComplete();
    }

}
