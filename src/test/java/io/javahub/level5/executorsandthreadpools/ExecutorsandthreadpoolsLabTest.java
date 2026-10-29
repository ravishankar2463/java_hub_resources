package io.javahub.level5.executorsandthreadpools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 5: Executors and Thread Pools.
 */
public class ExecutorsandthreadpoolsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ExecutorsandthreadpoolsLab lab = new ExecutorsandthreadpoolsLab();

        assertThat(lab.getLevel()).isEqualTo(5);
        assertThat(lab.getTopicTitle()).isEqualTo("Executors and Thread Pools");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
