package io.javahub.level5.thejavamemorymodeljmm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 5: The Java Memory Model (JMM).
 */
public class ThejavamemorymodeljmmLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThejavamemorymodeljmmLab lab = new ThejavamemorymodeljmmLab();

        assertThat(lab.getLevel()).isEqualTo(5);
        assertThat(lab.getTopicTitle()).isEqualTo("The Java Memory Model (JMM)");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
