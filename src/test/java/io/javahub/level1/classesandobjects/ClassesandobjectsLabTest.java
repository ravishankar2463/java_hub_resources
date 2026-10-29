package io.javahub.level1.classesandobjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Classes and Objects.
 */
public class ClassesandobjectsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ClassesandobjectsLab lab = new ClassesandobjectsLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Classes and Objects");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
