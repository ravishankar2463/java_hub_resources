package io.javahub.level9.highperformancegrpcinjava;

import java.nio.ByteBuffer;

/**
 * Emulates Protobuf varint binary wire packing for gRPC remote procedure calls.
 */
public class GrpcProtobufCodecSimulator {

    public byte[] encodeOrderRpc(int orderId, int amountCents) {
        // Tag 1 (orderId), Tag 2 (amountCents) packed in binary format
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put((byte) 0x08).putInt(orderId);
        buffer.put((byte) 0x10).putInt(amountCents);
        buffer.flip();

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }
}
