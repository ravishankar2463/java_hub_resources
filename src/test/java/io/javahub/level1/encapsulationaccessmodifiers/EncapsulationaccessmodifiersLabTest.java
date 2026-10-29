package io.javahub.level1.encapsulationaccessmodifiers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Encapsulation & Access Modifiers.
 */
public class EncapsulationaccessmodifiersLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        EncapsulationaccessmodifiersLab lab = new EncapsulationaccessmodifiersLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Encapsulation & Access Modifiers");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
