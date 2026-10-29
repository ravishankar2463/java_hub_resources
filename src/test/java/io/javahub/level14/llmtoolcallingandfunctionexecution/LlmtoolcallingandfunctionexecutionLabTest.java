package io.javahub.level14.llmtoolcallingandfunctionexecution;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 14: LLM Tool Calling and Function Execution.
 */
public class LlmtoolcallingandfunctionexecutionLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        LlmtoolcallingandfunctionexecutionLab lab = new LlmtoolcallingandfunctionexecutionLab();

        assertThat(lab.getLevel()).isEqualTo(14);
        assertThat(lab.getTopicTitle()).isEqualTo("LLM Tool Calling and Function Execution");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
