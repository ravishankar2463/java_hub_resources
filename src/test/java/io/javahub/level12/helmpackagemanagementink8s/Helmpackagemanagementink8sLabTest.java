package io.javahub.level12.helmpackagemanagementink8s;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 12: Helm Package Management in K8s.
 */
public class Helmpackagemanagementink8sLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        Helmpackagemanagementink8sLab lab = new Helmpackagemanagementink8sLab();

        assertThat(lab.getLevel()).isEqualTo(12);
        assertThat(lab.getTopicTitle()).isEqualTo("Helm Package Management in K8s");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
