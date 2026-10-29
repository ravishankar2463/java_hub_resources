package io.javahub.level11.resiliencypatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 11: Resiliency Patterns.
 */
public class ResiliencypatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ResiliencypatternsLab lab = new ResiliencypatternsLab();

        assertThat(lab.getLevel()).isEqualTo(11);
        assertThat(lab.getTopicTitle()).isEqualTo("Resiliency Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
