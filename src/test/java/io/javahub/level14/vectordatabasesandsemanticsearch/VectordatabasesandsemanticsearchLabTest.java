package io.javahub.level14.vectordatabasesandsemanticsearch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 14: Vector Databases and Semantic Search.
 */
public class VectordatabasesandsemanticsearchLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        VectordatabasesandsemanticsearchLab lab = new VectordatabasesandsemanticsearchLab();

        assertThat(lab.getLevel()).isEqualTo(14);
        assertThat(lab.getTopicTitle()).isEqualTo("Vector Databases and Semantic Search");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
