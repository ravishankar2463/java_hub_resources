package io.javahub.level7.jpaandhibernatebasics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: JPA and Hibernate Basics.
 */
public class JpaandhibernatebasicsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        JpaandhibernatebasicsLab lab = new JpaandhibernatebasicsLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("JPA and Hibernate Basics");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
