package io.javahub.level3.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 3: Generics.
 */
public class GenericsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        GenericsLab lab = new GenericsLab();

        assertThat(lab.getLevel()).isEqualTo(3);
        assertThat(lab.getTopicTitle()).isEqualTo("Generics");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
