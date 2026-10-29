package io.javahub.level1.memoryreferencescopying;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Memory References & Copying.
 */
public class MemoryreferencescopyingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        MemoryreferencescopyingLab lab = new MemoryreferencescopyingLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Memory References & Copying");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
