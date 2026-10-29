package io.javahub.level2.moderncoreapis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 2: Modern Core APIs.
 */
public class ModerncoreapisLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ModerncoreapisLab lab = new ModerncoreapisLab();

        assertThat(lab.getLevel()).isEqualTo(2);
        assertThat(lab.getTopicTitle()).isEqualTo("Modern Core APIs");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
