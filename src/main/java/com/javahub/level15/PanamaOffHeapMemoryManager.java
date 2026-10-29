package com.javahub.level15;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

/**
 * Ultra-Low Latency: Project Panama (Foreign Function & Memory API - JEP 454).
 * Demonstrates deterministic, off-heap native memory allocation, layout-aware slicing,
 * and zero-GC deallocation using Java 21 Arena.
 */
public class PanamaOffHeapMemoryManager {

    public record MemoryStatistics(long allocatedBytes, boolean isAccessible) {}

    public static long calculateOffHeapSum(int elementCount) {
        // Confined Arena provides deterministic lifecycle with zero GC overhead
        try (Arena arena = Arena.ofConfined()) {
            long byteSize = (long) elementCount * ValueLayout.JAVA_LONG.byteSize();
            MemorySegment segment = arena.allocate(byteSize);

            // Populate off-heap memory
            for (int i = 0; i < elementCount; i++) {
                segment.setAtIndex(ValueLayout.JAVA_LONG, i, (long) i * 10);
            }

            // Compute sum from off-heap segment
            long sum = 0;
            for (int i = 0; i < elementCount; i++) {
                sum += segment.getAtIndex(ValueLayout.JAVA_LONG, i);
            }

            return sum;
        } // Arena closes here: native memory is immediately released back to OS
    }

    public static byte[] copyOffHeapUtf8String(String text) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeString = arena.allocateFrom(text);
            byte[] buffer = new byte[(int) nativeString.byteSize() - 1]; // omit null terminator
            MemorySegment.copy(nativeString, ValueLayout.JAVA_BYTE, 0, buffer, 0, buffer.length);
            return buffer;
        }
    }
}
