package io.javahub.level12.cloudnativeserverlessjava;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 12: Cloud Native & Serverless Java.
 */
public class CloudnativeserverlessjavaLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        CloudnativeserverlessjavaLab lab = new CloudnativeserverlessjavaLab();

        assertThat(lab.getLevel()).isEqualTo(12);
        assertThat(lab.getTopicTitle()).isEqualTo("Cloud Native & Serverless Java");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
