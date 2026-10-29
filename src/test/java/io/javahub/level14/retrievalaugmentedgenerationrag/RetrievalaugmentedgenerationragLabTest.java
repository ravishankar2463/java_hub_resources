package io.javahub.level14.retrievalaugmentedgenerationrag;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 14: Retrieval-Augmented Generation (RAG).
 */
public class RetrievalaugmentedgenerationragLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        RetrievalaugmentedgenerationragLab lab = new RetrievalaugmentedgenerationragLab();

        assertThat(lab.getLevel()).isEqualTo(14);
        assertThat(lab.getTopicTitle()).isEqualTo("Retrieval-Augmented Generation (RAG)");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
