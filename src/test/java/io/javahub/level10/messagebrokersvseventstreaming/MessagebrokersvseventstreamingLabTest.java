package io.javahub.level10.messagebrokersvseventstreaming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 10: Message Brokers vs Event Streaming.
 */
public class MessagebrokersvseventstreamingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        MessagebrokersvseventstreamingLab lab = new MessagebrokersvseventstreamingLab();

        assertThat(lab.getLevel()).isEqualTo(10);
        assertThat(lab.getTopicTitle()).isEqualTo("Message Brokers vs Event Streaming");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
