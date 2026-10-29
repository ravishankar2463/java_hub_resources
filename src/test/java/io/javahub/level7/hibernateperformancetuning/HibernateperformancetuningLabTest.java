package io.javahub.level7.hibernateperformancetuning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: Hibernate Performance Tuning.
 */
public class HibernateperformancetuningLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        HibernateperformancetuningLab lab = new HibernateperformancetuningLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("Hibernate Performance Tuning");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
