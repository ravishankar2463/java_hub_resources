package io.javahub.level8.automatedtesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Automated Testing.
 */
public class AutomatedtestingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        AutomatedtestingLab lab = new AutomatedtestingLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Automated Testing");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
