package io.javahub.level14.theailandscapeforjavaspringai;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 14: The AI Landscape for Java: Spring AI.
 */
public class TheailandscapeforjavaspringaiLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        TheailandscapeforjavaspringaiLab lab = new TheailandscapeforjavaspringaiLab();

        assertThat(lab.getLevel()).isEqualTo(14);
        assertThat(lab.getTopicTitle()).isEqualTo("The AI Landscape for Java: Spring AI");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
