package io.javahub.level1.inheritance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Inheritance.
 */
public class InheritanceLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        InheritanceLab lab = new InheritanceLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Inheritance");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
