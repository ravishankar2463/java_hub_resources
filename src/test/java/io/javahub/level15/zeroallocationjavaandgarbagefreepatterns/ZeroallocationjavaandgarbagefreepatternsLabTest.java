package io.javahub.level15.zeroallocationjavaandgarbagefreepatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 15: Zero-Allocation Java and Garbage-Free Patterns.
 */
public class ZeroallocationjavaandgarbagefreepatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ZeroallocationjavaandgarbagefreepatternsLab lab = new ZeroallocationjavaandgarbagefreepatternsLab();

        assertThat(lab.getLevel()).isEqualTo(15);
        assertThat(lab.getTopicTitle()).isEqualTo("Zero-Allocation Java and Garbage-Free Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
