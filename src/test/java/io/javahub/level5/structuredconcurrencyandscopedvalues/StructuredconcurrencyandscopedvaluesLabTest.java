package io.javahub.level5.structuredconcurrencyandscopedvalues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 5: Structured Concurrency and Scoped Values.
 */
public class StructuredconcurrencyandscopedvaluesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        StructuredconcurrencyandscopedvaluesLab lab = new StructuredconcurrencyandscopedvaluesLab();

        assertThat(lab.getLevel()).isEqualTo(5);
        assertThat(lab.getTopicTitle()).isEqualTo("Structured Concurrency and Scoped Values");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
