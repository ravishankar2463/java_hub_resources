package com.javahub.level15;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ultra-Low Latency: Memory-Mapped IPC Queue Mechanics (Chronicle Queue & Aeron).
 * Simulates microsecond-level IPC message publishing and reading over a shared direct memory buffer.
 */
public class ChronicleMemoryMappedQueue {

    private final ByteBuffer mappedBuffer;
    private final AtomicInteger writeIndex = new AtomicInteger(0);
    private int readIndex = 0;

    public ChronicleMemoryMappedQueue(int capacityBytes) {
        this.mappedBuffer = ByteBuffer.allocateDirect(capacityBytes);
    }

    public synchronized boolean writeMessage(String payload) {
        byte[] bytes = payload.getBytes();
        int required = 4 + bytes.length; // 4 bytes length prefix + payload

        if (writeIndex.get() + required > mappedBuffer.capacity()) {
            return false; // Out of memory-mapped buffer space
        }

        mappedBuffer.position(writeIndex.get());
        mappedBuffer.putInt(bytes.length);
        mappedBuffer.put(bytes);
        writeIndex.addAndGet(required);
        return true;
    }

    public synchronized String readNextMessage() {
        if (readIndex >= writeIndex.get()) {
            return null; // Caught up to tail
        }

        mappedBuffer.position(readIndex);
        int length = mappedBuffer.getInt();
        byte[] bytes = new byte[length];
        mappedBuffer.get(bytes);
        readIndex += (4 + length);

        return new String(bytes);
    }

    public synchronized int unreadBytes() {
        return writeIndex.get() - readIndex;
    }
}
