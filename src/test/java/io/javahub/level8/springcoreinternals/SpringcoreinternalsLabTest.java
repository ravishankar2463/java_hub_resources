package io.javahub.level8.springcoreinternals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Spring Core Internals.
 */
public class SpringcoreinternalsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SpringcoreinternalsLab lab = new SpringcoreinternalsLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Spring Core Internals");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
