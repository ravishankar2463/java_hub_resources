package io.javahub.level15.ultralowlatencymessagingaeronandchroniclequeue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 15: Ultra-Low-Latency Messaging: Aeron and Chronicle Queue.
 */
public class UltralowlatencymessagingaeronandchroniclequeueLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        UltralowlatencymessagingaeronandchroniclequeueLab lab = new UltralowlatencymessagingaeronandchroniclequeueLab();

        assertThat(lab.getLevel()).isEqualTo(15);
        assertThat(lab.getTopicTitle()).isEqualTo("Ultra-Low-Latency Messaging: Aeron and Chronicle Queue");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
