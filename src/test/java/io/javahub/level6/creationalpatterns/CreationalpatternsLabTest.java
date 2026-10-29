package io.javahub.level6.creationalpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 6: Creational Patterns.
 */
public class CreationalpatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        CreationalpatternsLab lab = new CreationalpatternsLab();

        assertThat(lab.getLevel()).isEqualTo(6);
        assertThat(lab.getTopicTitle()).isEqualTo("Creational Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
