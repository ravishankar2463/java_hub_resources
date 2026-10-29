package io.javahub.level11.distributedratelimitingandthrottling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 11: Distributed Rate Limiting and Throttling.
 */
public class DistributedratelimitingandthrottlingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        DistributedratelimitingandthrottlingLab lab = new DistributedratelimitingandthrottlingLab();

        assertThat(lab.getLevel()).isEqualTo(11);
        assertThat(lab.getTopicTitle()).isEqualTo("Distributed Rate Limiting and Throttling");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
