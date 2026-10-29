package io.javahub.level8.springdatajpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Spring Data JPA.
 */
public class SpringdatajpaLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SpringdatajpaLab lab = new SpringdatajpaLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Spring Data JPA");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
