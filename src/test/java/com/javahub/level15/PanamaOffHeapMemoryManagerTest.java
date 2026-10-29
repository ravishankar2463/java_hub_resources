package com.javahub.level15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import static org.assertj.core.api.Assertions.*;

class PanamaOffHeapMemoryManagerTest {

    @Test
    @DisplayName("Verify Project Panama off-heap memory allocation and zero-GC sum calculation")
    void testPanamaOffHeapSum() {
        int count = 1_000;
        long sum = PanamaOffHeapMemoryManager.calculateOffHeapSum(count);

        // Sum of (i * 10) for i in 0..999 = 10 * (999 * 1000 / 2) = 4,995,000
        assertThat(sum).isEqualTo(4_995_000L);
    }

    @Test
    @DisplayName("Verify native string memory copying using MemorySegment")
    void testPanamaStringCopy() {
        String input = "Zero-Copy Panama Off-Heap Engine";
        byte[] bytes = PanamaOffHeapMemoryManager.copyOffHeapUtf8String(input);

        String recovered = new String(bytes, StandardCharsets.UTF_8);
        assertThat(recovered).isEqualTo(input);
    }
}
