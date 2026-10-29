package io.javahub.level1.sealedclassesandpatternmatching;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 1: Sealed Classes and Pattern Matching.
 */
public class SealedclassesandpatternmatchingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        SealedclassesandpatternmatchingLab lab = new SealedclassesandpatternmatchingLab();

        assertThat(lab.getLevel()).isEqualTo(1);
        assertThat(lab.getTopicTitle()).isEqualTo("Sealed Classes and Pattern Matching");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
