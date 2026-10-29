package io.javahub.level13.systemdesignscalabilitypatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 13: System Design: Scalability Patterns.
 */
public class SystemdesignscalabilitypatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SystemdesignscalabilitypatternsLab lab = new SystemdesignscalabilitypatternsLab();

        assertThat(lab.getLevel()).isEqualTo(13);
        assertThat(lab.getTopicTitle()).isEqualTo("System Design: Scalability Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
