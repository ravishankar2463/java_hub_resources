package com.javahub.level15;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Ultra-Low Latency: LMAX Disruptor & Lock-Free Ring Buffer Pattern.
 * Implements a high-throughput bounded circular ring buffer with bitwise sequence masking,
 * eliminating lock contention and maximizing CPU pipeline efficiency.
 */
public class LmaxDisruptorRingBuffer<T> {

    private final Object[] ringBuffer;
    private final int bufferSize;
    private final int indexMask;
    private final AtomicLong cursor = new AtomicLong(-1);

    @SuppressWarnings("unchecked")
    public LmaxDisruptorRingBuffer(int powerOfTwoSize) {
        if (Integer.bitCount(powerOfTwoSize) != 1) {
            throw new IllegalArgumentException("Ring buffer size must be a power of two");
        }
        this.bufferSize = powerOfTwoSize;
        this.indexMask = powerOfTwoSize - 1;
        this.ringBuffer = new Object[bufferSize];
    }

    public long next() {
        return cursor.incrementAndGet();
    }

    public void publish(long sequence, T event) {
        int index = (int) (sequence & indexMask);
        ringBuffer[index] = event;
    }

    @SuppressWarnings("unchecked")
    public T get(long sequence) {
        int index = (int) (sequence & indexMask);
        return (T) ringBuffer[index];
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public long getCursor() {
        return cursor.get();
    }
}
