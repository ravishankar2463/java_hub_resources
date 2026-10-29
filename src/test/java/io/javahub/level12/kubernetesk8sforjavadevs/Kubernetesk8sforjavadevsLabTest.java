package io.javahub.level12.kubernetesk8sforjavadevs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 12: Kubernetes (K8s) for Java Devs.
 */
public class Kubernetesk8sforjavadevsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        Kubernetesk8sforjavadevsLab lab = new Kubernetesk8sforjavadevsLab();

        assertThat(lab.getLevel()).isEqualTo(12);
        assertThat(lab.getTopicTitle()).isEqualTo("Kubernetes (K8s) for Java Devs");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
