package io.javahub.level15.disruptor;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/**
 * High-performance lock-free ring buffer demonstrating:
 * 1. Power-of-two size sequence masking (replacing expensive modulo operations with bitwise AND)
 * 2. Pre-allocated mutable event slots (Zero garbage collection churn)
 */
public class LockFreeRingBuffer<T> {

    private final Object[] entries;
    private final int mask;
    private final AtomicLong cursor = new AtomicLong(-1);

    public LockFreeRingBuffer(int capacity, Supplier<T> eventFactory) {
        if (Integer.bitCount(capacity) != 1) {
            throw new IllegalArgumentException("Capacity must be a power of 2");
        }
        this.mask = capacity - 1;
        this.entries = new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            this.entries[i] = eventFactory.get();
        }
    }

    public long nextSequence() {
        return cursor.incrementAndGet();
    }

    @SuppressWarnings("unchecked")
    public T get(long sequence) {
        int index = (int) (sequence & mask); // Fast bitwise masking!
        return (T) entries[index];
    }

    public void publish(long sequence) {
        // In full Disruptor, memory barriers flush the published sequence to consumers
    }

    public int getCapacity() {
        return entries.length;
    }
}
