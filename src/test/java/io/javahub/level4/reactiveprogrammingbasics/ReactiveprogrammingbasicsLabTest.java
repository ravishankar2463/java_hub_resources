package io.javahub.level4.reactiveprogrammingbasics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 4: Reactive Programming Basics.
 */
public class ReactiveprogrammingbasicsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ReactiveprogrammingbasicsLab lab = new ReactiveprogrammingbasicsLab();

        assertThat(lab.getLevel()).isEqualTo(4);
        assertThat(lab.getTopicTitle()).isEqualTo("Reactive Programming Basics");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
