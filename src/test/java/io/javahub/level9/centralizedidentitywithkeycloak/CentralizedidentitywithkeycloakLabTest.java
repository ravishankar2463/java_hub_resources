package io.javahub.level9.centralizedidentitywithkeycloak;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 9: Centralized Identity with Keycloak.
 */
public class CentralizedidentitywithkeycloakLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        CentralizedidentitywithkeycloakLab lab = new CentralizedidentitywithkeycloakLab();

        assertThat(lab.getLevel()).isEqualTo(9);
        assertThat(lab.getTopicTitle()).isEqualTo("Centralized Identity with Keycloak");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
