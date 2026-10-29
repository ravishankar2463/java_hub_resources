package io.javahub.level6.domaindrivendesigntacticalpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 6: Domain-Driven Design Tactical Patterns.
 */
public class DomaindrivendesigntacticalpatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        DomaindrivendesigntacticalpatternsLab lab = new DomaindrivendesigntacticalpatternsLab();

        assertThat(lab.getLevel()).isEqualTo(6);
        assertThat(lab.getTopicTitle()).isEqualTo("Domain-Driven Design Tactical Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
