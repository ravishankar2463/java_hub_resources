package io.javahub.level2.ioandnio2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 2: I/O and NIO.2.
 */
public class Ioandnio2LabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        Ioandnio2Lab lab = new Ioandnio2Lab();

        assertThat(lab.getLevel()).isEqualTo(2);
        assertThat(lab.getTopicTitle()).isEqualTo("I/O and NIO.2");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
