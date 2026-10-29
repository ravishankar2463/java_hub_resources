package io.javahub.level11.longrunningworkflowswithtemporal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 11: Long-Running Workflows with Temporal.
 */
public class LongrunningworkflowswithtemporalLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        LongrunningworkflowswithtemporalLab lab = new LongrunningworkflowswithtemporalLab();

        assertThat(lab.getLevel()).isEqualTo(11);
        assertThat(lab.getTopicTitle()).isEqualTo("Long-Running Workflows with Temporal");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
