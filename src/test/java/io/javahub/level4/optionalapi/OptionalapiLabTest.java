package io.javahub.level4.optionalapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 4: Optional API.
 */
public class OptionalapiLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        OptionalapiLab lab = new OptionalapiLab();

        assertThat(lab.getLevel()).isEqualTo(4);
        assertThat(lab.getTopicTitle()).isEqualTo("Optional API");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
