package io.javahub.level12.dockerandcontainerization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 12: Docker and Containerization.
 */
public class DockerandcontainerizationLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        DockerandcontainerizationLab lab = new DockerandcontainerizationLab();

        assertThat(lab.getLevel()).isEqualTo(12);
        assertThat(lab.getTopicTitle()).isEqualTo("Docker and Containerization");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
