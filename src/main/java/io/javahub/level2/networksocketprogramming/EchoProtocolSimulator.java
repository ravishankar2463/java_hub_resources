package io.javahub.level2.networksocketprogramming;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Emulates TCP network socket request-response streams.
 */
public class EchoProtocolSimulator {

    public String processEchoStream(String inputMessage) throws IOException {
        byte[] incomingBytes = inputMessage.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream clientIn = new ByteArrayInputStream(incomingBytes);
        ByteArrayOutputStream serverOut = new ByteArrayOutputStream();

        // Server reads client bytes and echoes with prefix
        byte[] buffer = new byte[1024];
        int bytesRead = clientIn.read(buffer);
        if (bytesRead > 0) {
            serverOut.write("ECHO: ".getBytes(StandardCharsets.UTF_8));
            serverOut.write(buffer, 0, bytesRead);
        }

        return serverOut.toString(StandardCharsets.UTF_8);
    }
}
