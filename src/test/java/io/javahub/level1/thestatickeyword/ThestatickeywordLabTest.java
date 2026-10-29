package io.javahub.level1.thestatickeyword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: The Static Keyword.
 */
public class ThestatickeywordLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThestatickeywordLab lab = new ThestatickeywordLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("The Static Keyword");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
