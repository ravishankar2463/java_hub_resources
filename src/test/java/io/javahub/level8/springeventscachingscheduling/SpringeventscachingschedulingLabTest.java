package io.javahub.level8.springeventscachingscheduling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Spring Events, Caching, & Scheduling.
 */
public class SpringeventscachingschedulingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SpringeventscachingschedulingLab lab = new SpringeventscachingschedulingLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Spring Events, Caching, & Scheduling");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
