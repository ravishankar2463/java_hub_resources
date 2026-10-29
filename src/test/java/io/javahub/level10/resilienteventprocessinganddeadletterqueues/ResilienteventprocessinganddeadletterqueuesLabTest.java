package io.javahub.level10.resilienteventprocessinganddeadletterqueues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Resilient Event Processing and Dead Letter Queues.
 */
public class ResilienteventprocessinganddeadletterqueuesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ResilienteventprocessinganddeadletterqueuesLab lab = new ResilienteventprocessinganddeadletterqueuesLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Resilient Event Processing and Dead Letter Queues");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
