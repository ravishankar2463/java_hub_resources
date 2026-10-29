package io.javahub.level2.ioandnio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * High-speed NIO.2 buffer manipulations and file path utilities.
 */
public class NioFastFileProcessor {

    public void writeAndReadBuffer(Path tempFile, String message) throws IOException {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        Files.write(tempFile, bytes);
    }

    public String readViaDirectBuffer(Path tempFile) throws IOException {
        byte[] bytes = Files.readAllBytes(tempFile);
        ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
        buffer.put(bytes);
        buffer.flip(); // Prepare for reading

        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return new String(result, StandardCharsets.UTF_8);
    }
}
