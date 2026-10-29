package io.javahub.level9.highperformancegrpcinjava;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GrpcProtobufCodecSimulatorTest {

    @Test
    @DisplayName("Protobuf binary wire should be significantly more compact than JSON string payload")
    void testProtobufCompactness() {
        var codec = new GrpcProtobufCodecSimulator();
        byte[] binaryWire = codec.encodeOrderRpc(1001, 4999);
        String jsonEquivalent = "{\"orderId\":1001,\"amountCents\":4999}";

        assertThat(binaryWire.length).isLessThan(jsonEquivalent.getBytes().length);
    }
}
