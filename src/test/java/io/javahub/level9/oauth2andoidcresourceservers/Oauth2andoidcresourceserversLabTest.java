package io.javahub.level9.oauth2andoidcresourceservers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 9: OAuth2 and OIDC Resource Servers.
 */
public class Oauth2andoidcresourceserversLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        Oauth2andoidcresourceserversLab lab = new Oauth2andoidcresourceserversLab();

        assertThat(lab.getLevel()).isEqualTo(9);
        assertThat(lab.getTopicTitle()).isEqualTo("OAuth2 and OIDC Resource Servers");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
