package io.javahub.level8.buildtools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Build Tools.
 */
public class BuildtoolsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        BuildtoolsLab lab = new BuildtoolsLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Build Tools");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
