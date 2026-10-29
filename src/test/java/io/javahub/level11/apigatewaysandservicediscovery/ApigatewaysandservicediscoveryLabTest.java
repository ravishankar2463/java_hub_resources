package io.javahub.level11.apigatewaysandservicediscovery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 11: API Gateways and Service Discovery.
 */
public class ApigatewaysandservicediscoveryLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ApigatewaysandservicediscoveryLab lab = new ApigatewaysandservicediscoveryLab();

        assertThat(lab.getLevel()).isEqualTo(11);
        assertThat(lab.getTopicTitle()).isEqualTo("API Gateways and Service Discovery");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
