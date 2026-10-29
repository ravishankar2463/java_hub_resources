package io.javahub.level9.highperformancegrpcinjava;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 9: High-Performance gRPC in Java.
 */
public class HighperformancegrpcinjavaLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        HighperformancegrpcinjavaLab lab = new HighperformancegrpcinjavaLab();

        assertThat(lab.getLevel()).isEqualTo(9);
        assertThat(lab.getTopicTitle()).isEqualTo("High-Performance gRPC in Java");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
