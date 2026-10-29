package io.javahub.level9.realtimemessagingwebsocketssse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 9: Real-Time Messaging: WebSockets & SSE.
 */
public class RealtimemessagingwebsocketssseLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        RealtimemessagingwebsocketssseLab lab = new RealtimemessagingwebsocketssseLab();

        assertThat(lab.getLevel()).isEqualTo(9);
        assertThat(lab.getTopicTitle()).isEqualTo("Real-Time Messaging: WebSockets & SSE");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
