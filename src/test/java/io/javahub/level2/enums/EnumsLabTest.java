package io.javahub.level2.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 2: Enums.
 */
public class EnumsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        EnumsLab lab = new EnumsLab();

        assertThat(lab.getLevel()).isEqualTo(2);
        assertThat(lab.getTopicTitle()).isEqualTo("Enums");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
