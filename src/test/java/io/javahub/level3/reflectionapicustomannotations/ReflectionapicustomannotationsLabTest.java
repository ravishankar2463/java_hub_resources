package io.javahub.level3.reflectionapicustomannotations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 3: Reflection API & Custom Annotations.
 */
public class ReflectionapicustomannotationsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ReflectionapicustomannotationsLab lab = new ReflectionapicustomannotationsLab();

        assertThat(lab.getLevel()).isEqualTo(3);
        assertThat(lab.getTopicTitle()).isEqualTo("Reflection API & Custom Annotations");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
