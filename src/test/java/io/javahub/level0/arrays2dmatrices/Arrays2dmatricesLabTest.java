package io.javahub.level0.arrays2dmatrices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 0: Arrays & 2D Matrices.
 */
public class Arrays2dmatricesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        Arrays2dmatricesLab lab = new Arrays2dmatricesLab();

        assertThat(lab.getLevel()).isEqualTo(0);
        assertThat(lab.getTopicTitle()).isEqualTo("Arrays & 2D Matrices");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
