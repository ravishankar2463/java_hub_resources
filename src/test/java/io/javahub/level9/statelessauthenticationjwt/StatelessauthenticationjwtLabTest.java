package io.javahub.level9.statelessauthenticationjwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 9: Stateless Authentication (JWT).
 */
public class StatelessauthenticationjwtLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        StatelessauthenticationjwtLab lab = new StatelessauthenticationjwtLab();

        assertThat(lab.getLevel()).isEqualTo(9);
        assertThat(lab.getTopicTitle()).isEqualTo("Stateless Authentication (JWT)");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
