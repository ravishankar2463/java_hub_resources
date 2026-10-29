package com.javahub.grpc.codec;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Emulates high-efficiency Protobuf binary serialization framing for gRPC.
 */
public class ProtobufBinaryCodec {

    public record OrderWireFrame(long orderId, String symbol, double price, int quantity) {}

    public byte[] serialize(OrderWireFrame order) {
        byte[] symBytes = order.symbol().getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocate(8 + 4 + symBytes.length + 8 + 4);
        buffer.putLong(order.orderId());
        buffer.putInt(symBytes.length);
        buffer.put(symBytes);
        buffer.putDouble(order.price());
        buffer.putInt(order.quantity());
        return buffer.array();
    }

    public OrderWireFrame deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        long id = buffer.getLong();
        int symLen = buffer.getInt();
        byte[] symBytes = new byte[symLen];
        buffer.get(symBytes);
        String symbol = new String(symBytes, StandardCharsets.UTF_8);
        double price = buffer.getDouble();
        int qty = buffer.getInt();
        return new OrderWireFrame(id, symbol, price, qty);
    }
}
