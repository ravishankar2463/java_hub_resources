package io.javahub.level15.thelmaxdisruptorandlockfreeringbuffers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 15: The LMAX Disruptor and Lock-Free Ring Buffers.
 */
public class ThelmaxdisruptorandlockfreeringbuffersLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThelmaxdisruptorandlockfreeringbuffersLab lab = new ThelmaxdisruptorandlockfreeringbuffersLab();

        assertThat(lab.getLevel()).isEqualTo(15);
        assertThat(lab.getTopicTitle()).isEqualTo("The LMAX Disruptor and Lock-Free Ring Buffers");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
