package io.javahub.level8.aspectorientedprogrammingaop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ExecutionTimeAspectSimulatorTest {

    @Test
    @DisplayName("Aspect should execute join point and report execution duration in nanoseconds")
    void testAspectProfiling() {
        var aspect = new ExecutionTimeAspectSimulator();

        var execution = aspect.profileMethod(() -> {
            int acc = 0;
            for (int i = 0; i < 1000; i++) acc += i;
            return acc;
        });

        assertThat(execution.result()).isEqualTo(499500);
        assertThat(execution.durationNanos()).isGreaterThan(0L);
    }
}
