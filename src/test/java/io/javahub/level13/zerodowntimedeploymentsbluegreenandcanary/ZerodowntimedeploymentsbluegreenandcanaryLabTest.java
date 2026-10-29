package io.javahub.level13.zerodowntimedeploymentsbluegreenandcanary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 13: Zero-Downtime Deployments: Blue-Green and Canary.
 */
public class ZerodowntimedeploymentsbluegreenandcanaryLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ZerodowntimedeploymentsbluegreenandcanaryLab lab = new ZerodowntimedeploymentsbluegreenandcanaryLab();

        assertThat(lab.getLevel()).isEqualTo(13);
        assertThat(lab.getTopicTitle()).isEqualTo("Zero-Downtime Deployments: Blue-Green and Canary");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
