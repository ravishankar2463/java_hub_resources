package io.javahub.level14.llmintegrationandstructuredrecords;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 14: LLM Integration and Structured Records.
 */
public class LlmintegrationandstructuredrecordsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        LlmintegrationandstructuredrecordsLab lab = new LlmintegrationandstructuredrecordsLab();

        assertThat(lab.getLevel()).isEqualTo(14);
        assertThat(lab.getTopicTitle()).isEqualTo("LLM Integration and Structured Records");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
