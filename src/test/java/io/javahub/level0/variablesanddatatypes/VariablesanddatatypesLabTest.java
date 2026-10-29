package io.javahub.level0.variablesanddatatypes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 0: Variables and Data Types.
 */
public class VariablesanddatatypesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        VariablesanddatatypesLab lab = new VariablesanddatatypesLab();

        assertThat(lab.getLevel()).isEqualTo(0);
        assertThat(lab.getTopicTitle()).isEqualTo("Variables and Data Types");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
