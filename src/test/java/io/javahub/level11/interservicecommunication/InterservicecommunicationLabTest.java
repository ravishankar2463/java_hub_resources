package io.javahub.level11.interservicecommunication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 11: Inter-Service Communication.
 */
public class InterservicecommunicationLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        InterservicecommunicationLab lab = new InterservicecommunicationLab();

        assertThat(lab.getLevel()).isEqualTo(11);
        assertThat(lab.getTopicTitle()).isEqualTo("Inter-Service Communication");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
