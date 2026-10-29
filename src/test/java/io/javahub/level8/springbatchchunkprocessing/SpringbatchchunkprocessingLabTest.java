package io.javahub.level8.springbatchchunkprocessing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Spring Batch: Chunk Processing.
 */
public class SpringbatchchunkprocessingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SpringbatchchunkprocessingLab lab = new SpringbatchchunkprocessingLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Spring Batch: Chunk Processing");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
