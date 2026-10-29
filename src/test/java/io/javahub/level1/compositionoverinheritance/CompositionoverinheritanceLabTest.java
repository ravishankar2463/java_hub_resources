package io.javahub.level1.compositionoverinheritance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Composition over Inheritance.
 */
public class CompositionoverinheritanceLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        CompositionoverinheritanceLab lab = new CompositionoverinheritanceLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Composition over Inheritance");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
