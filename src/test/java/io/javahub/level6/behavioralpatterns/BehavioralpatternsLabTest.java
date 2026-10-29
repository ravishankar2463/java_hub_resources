package io.javahub.level6.behavioralpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 6: Behavioral Patterns.
 */
public class BehavioralpatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        BehavioralpatternsLab lab = new BehavioralpatternsLab();

        assertThat(lab.getLevel()).isEqualTo(6);
        assertThat(lab.getTopicTitle()).isEqualTo("Behavioral Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
