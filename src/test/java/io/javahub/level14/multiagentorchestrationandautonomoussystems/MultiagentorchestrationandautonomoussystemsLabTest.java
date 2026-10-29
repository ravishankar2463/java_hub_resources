package io.javahub.level14.multiagentorchestrationandautonomoussystems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 14: Multi-Agent Orchestration and Autonomous Systems.
 */
public class MultiagentorchestrationandautonomoussystemsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        MultiagentorchestrationandautonomoussystemsLab lab = new MultiagentorchestrationandautonomoussystemsLab();

        assertThat(lab.getLevel()).isEqualTo(14);
        assertThat(lab.getTopicTitle()).isEqualTo("Multi-Agent Orchestration and Autonomous Systems");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
