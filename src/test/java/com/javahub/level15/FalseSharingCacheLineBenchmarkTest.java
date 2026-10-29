package com.javahub.level15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class FalseSharingCacheLineBenchmarkTest {

    @Test
    @DisplayName("Verify cache line padding structures and memory execution")
    void testCachePadding() {
        FalseSharingCacheLineBenchmark benchmark = new FalseSharingCacheLineBenchmark();
        int iterations = 100_000;

        long unpaddedNanos = benchmark.simulateUnpaddedContention(iterations);
        long paddedNanos = benchmark.simulatePaddedContention(iterations);

        assertThat(unpaddedNanos).isGreaterThan(0);
        assertThat(paddedNanos).isGreaterThan(0);

        FalseSharingCacheLineBenchmark.PaddedState padded = new FalseSharingCacheLineBenchmark.PaddedState();
        padded.value1 = 42L;
        padded.value2 = 84L;
        assertThat(padded.value1).isEqualTo(42L);
        assertThat(padded.value2).isEqualTo(84L);
    }
}
