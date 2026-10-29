package io.javahub.level6.structuralpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 6: Structural Patterns.
 */
public class StructuralpatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        StructuralpatternsLab lab = new StructuralpatternsLab();

        assertThat(lab.getLevel()).isEqualTo(6);
        assertThat(lab.getTopicTitle()).isEqualTo("Structural Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
