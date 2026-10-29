package com.javahub.level15;

/**
 * Ultra-Low Latency: Mechanical Sympathy & CPU Architecture.
 * Demonstrates CPU Cache Line boundaries (typically 64 bytes) and the performance
 * degradation caused by False Sharing across adjacent volatile fields.
 */
public class FalseSharingCacheLineBenchmark {

    /**
     * Unpadded holder: fields p1 and p2 reside within the same 64-byte L1 cache line,
     * triggering cache-coherency invalidate messages across CPU cores.
     */
    public static class UnpaddedState {
        public volatile long value1 = 0L;
        public volatile long value2 = 0L;
    }

    /**
     * Padded holder: 56 bytes of dummy padding prevent value1 and value2
     * from sharing the same CPU cache line.
     */
    public static class PaddedState {
        public volatile long value1 = 0L;
        // 7 longs * 8 bytes = 56 bytes padding to isolate into distinct 64-byte line
        public long p1, p2, p3, p4, p5, p6, p7;
        public volatile long value2 = 0L;
    }

    public long simulateUnpaddedContention(int iterations) {
        UnpaddedState state = new UnpaddedState();
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            state.value1++;
            state.value2++;
        }
        return System.nanoTime() - start;
    }

    public long simulatePaddedContention(int iterations) {
        PaddedState state = new PaddedState();
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            state.value1++;
            state.value2++;
        }
        return System.nanoTime() - start;
    }
}
