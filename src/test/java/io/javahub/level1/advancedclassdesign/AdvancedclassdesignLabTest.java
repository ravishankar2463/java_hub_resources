package io.javahub.level1.advancedclassdesign;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Advanced Class Design.
 */
public class AdvancedclassdesignLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        AdvancedclassdesignLab lab = new AdvancedclassdesignLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Advanced Class Design");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
