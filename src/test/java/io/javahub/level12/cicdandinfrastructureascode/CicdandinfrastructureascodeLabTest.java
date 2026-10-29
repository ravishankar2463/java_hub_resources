package io.javahub.level12.cicdandinfrastructureascode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 12: CI/CD and Infrastructure as Code.
 */
public class CicdandinfrastructureascodeLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        CicdandinfrastructureascodeLab lab = new CicdandinfrastructureascodeLab();

        assertThat(lab.getLevel()).isEqualTo(12);
        assertThat(lab.getTopicTitle()).isEqualTo("CI/CD and Infrastructure as Code");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
