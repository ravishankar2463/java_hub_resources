package io.javahub.level15.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class PanamaApp {

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("  Level 15: Project Panama Foreign Function & Memory API ");
        System.out.println("=========================================================\n");

        System.out.println("1. Allocating 1,024 Off-Heap integers using confined deterministic Arena...");
        try (Arena arena = Arena.ofConfined()) {
            // Allocate 4096 bytes (1024 ints * 4 bytes) directly off-heap (invisible to GC)
            MemorySegment segment = arena.allocate(ValueLayout.JAVA_INT, 1024);

            for (long i = 0; i < 1024; i++) {
                segment.setAtIndex(ValueLayout.JAVA_INT, i, (int) (i * 2));
            }

            int sample = segment.getAtIndex(ValueLayout.JAVA_INT, 500);
            System.out.println("  -> Successfully read value at index 500: " + sample);
            System.out.println("  -> Segment byte size: " + segment.byteSize() + " bytes");
            System.out.println("  -> Native address: 0x" + Long.toHexString(segment.address()));
        }
        // At this brace, the Arena closes and off-heap memory is instantly, deterministically freed back to the OS!
        System.out.println("2. Confined Arena exited. All native off-heap memory deterministically reclaimed with ZERO GC pause!");
    }
}
