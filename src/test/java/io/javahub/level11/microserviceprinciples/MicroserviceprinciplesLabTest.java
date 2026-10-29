package io.javahub.level11.microserviceprinciples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 11: Microservice Principles.
 */
public class MicroserviceprinciplesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        MicroserviceprinciplesLab lab = new MicroserviceprinciplesLab();

        assertThat(lab.getLevel()).isEqualTo(11);
        assertThat(lab.getTopicTitle()).isEqualTo("Microservice Principles");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
