package io.javahub.level12.googlejibdaemonlesscontainers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 12: Google Jib: Daemonless Containers.
 */
public class GooglejibdaemonlesscontainersLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        GooglejibdaemonlesscontainersLab lab = new GooglejibdaemonlesscontainersLab();

        assertThat(lab.getLevel()).isEqualTo(12);
        assertThat(lab.getTopicTitle()).isEqualTo("Google Jib: Daemonless Containers");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
