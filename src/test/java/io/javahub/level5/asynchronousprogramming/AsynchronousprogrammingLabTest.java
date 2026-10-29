package io.javahub.level5.asynchronousprogramming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 5: Asynchronous Programming.
 */
public class AsynchronousprogrammingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        AsynchronousprogrammingLab lab = new AsynchronousprogrammingLab();

        assertThat(lab.getLevel()).isEqualTo(5);
        assertThat(lab.getTopicTitle()).isEqualTo("Asynchronous Programming");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
