package io.javahub.level5.threadsafetyandsynchronization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 5: Thread Safety and Synchronization.
 */
public class ThreadsafetyandsynchronizationLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThreadsafetyandsynchronizationLab lab = new ThreadsafetyandsynchronizationLab();

        assertThat(lab.getLevel()).isEqualTo(5);
        assertThat(lab.getTopicTitle()).isEqualTo("Thread Safety and Synchronization");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
