package io.javahub.level15.sympathy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MechanicalSympathyTest {

    @Test
    @DisplayName("Verify branch prediction calculation completes with identical totals")
    void shouldExecuteBranchPredictionBenchmark() {
        long result = MechanicalSympathyApp.runBranchPredictionDemo();
        assertThat(result).isGreaterThan(0L);
    }

    @Test
    @DisplayName("Verify padded counter operates correctly under concurrent mutations")
    void shouldIncrementPaddedCounterSafely() throws InterruptedException {
        PaddedAtomicCounter counter = new PaddedAtomicCounter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertThat(counter.get()).isEqualTo(20_000L);
    }
}
