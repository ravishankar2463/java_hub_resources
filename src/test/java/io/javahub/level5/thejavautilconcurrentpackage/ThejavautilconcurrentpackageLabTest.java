package io.javahub.level5.thejavautilconcurrentpackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 5: The java.util.concurrent Package.
 */
public class ThejavautilconcurrentpackageLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ThejavautilconcurrentpackageLab lab = new ThejavautilconcurrentpackageLab();

        assertThat(lab.getLevel()).isEqualTo(5);
        assertThat(lab.getTopicTitle()).isEqualTo("The java.util.concurrent Package");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
