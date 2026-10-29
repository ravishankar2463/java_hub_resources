package io.javahub.level8.aspectorientedprogrammingaop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 8: Aspect-Oriented Programming (AOP).
 */
public class AspectorientedprogrammingaopLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        AspectorientedprogrammingaopLab lab = new AspectorientedprogrammingaopLab();

        assertThat(lab.getLevel()).isEqualTo(8);
        assertThat(lab.getTopicTitle()).isEqualTo("Aspect-Oriented Programming (AOP)");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
