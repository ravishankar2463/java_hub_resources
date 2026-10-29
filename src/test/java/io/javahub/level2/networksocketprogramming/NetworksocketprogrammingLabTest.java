package io.javahub.level2.networksocketprogramming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 2: Network Socket Programming.
 */
public class NetworksocketprogrammingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        NetworksocketprogrammingLab lab = new NetworksocketprogrammingLab();

        assertThat(lab.getLevel()).isEqualTo(2);
        assertThat(lab.getTopicTitle()).isEqualTo("Network Socket Programming");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
