package io.javahub.level0.environmentsetup;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 0: Environment Setup.
 */
public class EnvironmentsetupLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        EnvironmentsetupLab lab = new EnvironmentsetupLab();

        assertThat(lab.getLevel()).isEqualTo(0);
        assertThat(lab.getTopicTitle()).isEqualTo("Environment Setup");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
