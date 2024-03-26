package io.lettuce.core.output;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import io.lettuce.core.codec.StringCodec;

/**
 * @author Mark Paluch
 */
class SocketAddressOutputUnitTests {

    @Test
    void shouldReportSocketAddress() {

        SocketAddressOutput<String, String> output = new SocketAddressOutput<>(StringCodec.ASCII);

        output.set(ByteBuffer.wrap("localhost".getBytes()));
        output.set(ByteBuffer.wrap("6379".getBytes()));

        output.complete(0);

        assertThat(output.get()).isNotNull().isInstanceOf(InetSocketAddress.class);
    }
}
