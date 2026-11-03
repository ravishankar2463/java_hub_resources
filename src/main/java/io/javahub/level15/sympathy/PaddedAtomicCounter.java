package io.javahub.level15.sympathy;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Uses 64 bytes (8 longs) of padding to prevent false sharing with neighboring data
 * on modern x86/ARM64 64-byte L1 cache lines.
 */
public class PaddedAtomicCounter {

    // Cache line pre-padding (56 bytes)
    public volatile long p1, p2, p3, p4, p5, p6, p7;

    // The actual active value
    public final AtomicLong value = new AtomicLong();

    // Cache line post-padding (56 bytes)
    public volatile long q1, q2, q3, q4, q5, q6, q7;

    public void increment() {
        value.incrementAndGet();
    }

    public long get() {
        return value.get();
    }
}
