package io.javahub.level0.controlflow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 0: Control Flow.
 */
public class ControlflowLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ControlflowLab lab = new ControlflowLab();

        assertThat(lab.getLevel()).isEqualTo(0);
        assertThat(lab.getTopicTitle()).isEqualTo("Control Flow");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
