package io.javahub.level6.architecturalpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 6: Architectural Patterns.
 */
public class ArchitecturalpatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ArchitecturalpatternsLab lab = new ArchitecturalpatternsLab();

        assertThat(lab.getLevel()).isEqualTo(6);
        assertThat(lab.getTopicTitle()).isEqualTo("Architectural Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
