package io.javahub.level13.systemdesignobservability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 13: System Design: Observability.
 */
public class SystemdesignobservabilityLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SystemdesignobservabilityLab lab = new SystemdesignobservabilityLab();

        assertThat(lab.getLevel()).isEqualTo(13);
        assertThat(lab.getTopicTitle()).isEqualTo("System Design: Observability");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
