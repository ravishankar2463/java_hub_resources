package io.javahub.level1.abstraction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Abstraction.
 */
public class AbstractionLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        AbstractionLab lab = new AbstractionLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Abstraction");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
