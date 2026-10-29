package com.javahub.level15;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * Ultra-Low Latency: Zero-Allocation & Garbage-Free Object Pooling.
 * Demonstrates recycling direct off-heap ByteBuffers to achieve steady-state zero GC allocation.
 */
public class GarbageFreeByteBufferPool {

    private final int bufferCapacity;
    private final Queue<ByteBuffer> availableBuffers = new ArrayDeque<>();
    private final int poolLimit;

    public GarbageFreeByteBufferPool(int bufferCapacity, int poolLimit) {
        this.bufferCapacity = bufferCapacity;
        this.poolLimit = poolLimit;
        for (int i = 0; i < poolLimit; i++) {
            availableBuffers.add(ByteBuffer.allocateDirect(bufferCapacity));
        }
    }

    public synchronized ByteBuffer acquire() {
        ByteBuffer buf = availableBuffers.poll();
        if (buf == null) {
            return ByteBuffer.allocateDirect(bufferCapacity);
        }
        buf.clear();
        return buf;
    }

    public synchronized void release(ByteBuffer buffer) {
        Objects.requireNonNull(buffer, "Buffer cannot be null");
        if (availableBuffers.size() < poolLimit) {
            buffer.clear();
            availableBuffers.offer(buffer);
        }
    }

    public synchronized int availableCount() {
        return availableBuffers.size();
    }
}
