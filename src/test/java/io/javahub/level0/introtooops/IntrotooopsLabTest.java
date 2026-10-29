package io.javahub.level0.introtooops;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 0: Intro to OOPS.
 */
public class IntrotooopsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        IntrotooopsLab lab = new IntrotooopsLab();

        assertThat(lab.getLevel()).isEqualTo(0);
        assertThat(lab.getTopicTitle()).isEqualTo("Intro to OOPS");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
