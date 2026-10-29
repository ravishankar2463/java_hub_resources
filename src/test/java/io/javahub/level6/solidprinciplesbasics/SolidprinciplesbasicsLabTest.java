package io.javahub.level6.solidprinciplesbasics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 6: SOLID Principles Basics.
 */
public class SolidprinciplesbasicsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SolidprinciplesbasicsLab lab = new SolidprinciplesbasicsLab();

        assertThat(lab.getLevel()).isEqualTo(6);
        assertThat(lab.getTopicTitle()).isEqualTo("SOLID Principles Basics");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
