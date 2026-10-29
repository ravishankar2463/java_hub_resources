package com.javahub.grpc;

import com.javahub.grpc.codec.ProtobufBinaryCodec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GrpcCodecTests {

    private final ProtobufBinaryCodec codec = new ProtobufBinaryCodec();

    @Test
    @DisplayName("Protobuf binary wire framing should round-trip order data losslessly")
    void testSerializationRoundTrip() {
        var original = new ProtobufBinaryCodec.OrderWireFrame(998811L, "MSFT", 420.50, 100);
        byte[] bytes = codec.serialize(original);

        assertThat(bytes.length).isLessThan(40); // Protobuf wire compact efficiency

        var recovered = codec.deserialize(bytes);
        assertThat(recovered).isEqualTo(original);
    }
}
