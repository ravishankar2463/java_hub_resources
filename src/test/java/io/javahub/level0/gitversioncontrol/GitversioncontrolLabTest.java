package io.javahub.level0.gitversioncontrol;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 0: Git & Version Control.
 */
public class GitversioncontrolLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        GitversioncontrolLab lab = new GitversioncontrolLab();

        assertThat(lab.getLevel()).isEqualTo(0);
        assertThat(lab.getTopicTitle()).isEqualTo("Git & Version Control");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
