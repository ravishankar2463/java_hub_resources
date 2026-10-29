package io.javahub.level9.springsecurityarchitecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 9: Spring Security Architecture.
 */
public class SpringsecurityarchitectureLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SpringsecurityarchitectureLab lab = new SpringsecurityarchitectureLab();

        assertThat(lab.getLevel()).isEqualTo(9);
        assertThat(lab.getTopicTitle()).isEqualTo("Spring Security Architecture");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
