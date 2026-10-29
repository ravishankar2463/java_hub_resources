package io.javahub.level1.polymorphism;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Polymorphism.
 */
public class PolymorphismLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        PolymorphismLab lab = new PolymorphismLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Polymorphism");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
