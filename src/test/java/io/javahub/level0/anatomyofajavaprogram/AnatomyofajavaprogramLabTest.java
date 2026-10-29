package io.javahub.level0.anatomyofajavaprogram;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 0: Anatomy of a Java Program.
 */
public class AnatomyofajavaprogramLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        AnatomyofajavaprogramLab lab = new AnatomyofajavaprogramLab();

        assertThat(lab.getLevel()).isEqualTo(0);
        assertThat(lab.getTopicTitle()).isEqualTo("Anatomy of a Java Program");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
