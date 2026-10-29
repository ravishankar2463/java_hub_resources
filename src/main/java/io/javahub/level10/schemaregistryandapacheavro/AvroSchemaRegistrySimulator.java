package io.javahub.level10.schemaregistryandapacheavro;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Emulates Schema Registry 5-byte wire protocol: [Magic Byte 0x00] + [4-byte Schema ID].
 */
public class AvroSchemaRegistrySimulator {

    private final Map<Integer, String> registry = new HashMap<>();

    public void registerSchema(int id, String schemaJson) {
        registry.put(id, schemaJson);
    }

    public byte[] prependSchemaHeader(int schemaId, byte[] avroPayload) {
        ByteBuffer buffer = ByteBuffer.allocate(5 + avroPayload.length);
        buffer.put((byte) 0x00);       // Magic Byte
        buffer.putInt(schemaId);       // 4-Byte Schema ID
        buffer.put(avroPayload);
        return buffer.array();
    }

    public int extractSchemaId(byte[] payloadWithHeader) {
        ByteBuffer buffer = ByteBuffer.wrap(payloadWithHeader);
        byte magic = buffer.get();
        if (magic != 0x00) throw new IllegalArgumentException("Unknown magic byte: " + magic);
        return buffer.getInt();
    }
}
