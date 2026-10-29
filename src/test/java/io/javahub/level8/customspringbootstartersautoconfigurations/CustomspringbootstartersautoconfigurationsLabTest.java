package io.javahub.level8.customspringbootstartersautoconfigurations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Custom Spring Boot Starters & Auto-Configurations.
 */
public class CustomspringbootstartersautoconfigurationsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        CustomspringbootstartersautoconfigurationsLab lab = new CustomspringbootstartersautoconfigurationsLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Custom Spring Boot Starters & Auto-Configurations");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
