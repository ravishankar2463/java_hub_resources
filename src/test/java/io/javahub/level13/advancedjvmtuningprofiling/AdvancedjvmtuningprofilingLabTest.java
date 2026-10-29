package io.javahub.level13.advancedjvmtuningprofiling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 13: Advanced JVM Tuning & Profiling.
 */
public class AdvancedjvmtuningprofilingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        AdvancedjvmtuningprofilingLab lab = new AdvancedjvmtuningprofilingLab();

        assertThat(lab.getLevel()).isEqualTo(13);
        assertThat(lab.getTopicTitle()).isEqualTo("Advanced JVM Tuning & Profiling");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
