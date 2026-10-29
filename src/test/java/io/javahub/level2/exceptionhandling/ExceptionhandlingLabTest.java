package io.javahub.level2.exceptionhandling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 2: Exception Handling.
 */
public class ExceptionhandlingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ExceptionhandlingLab lab = new ExceptionhandlingLab();

        assertThat(lab.getLevel()).isEqualTo(2);
        assertThat(lab.getTopicTitle()).isEqualTo("Exception Handling");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
