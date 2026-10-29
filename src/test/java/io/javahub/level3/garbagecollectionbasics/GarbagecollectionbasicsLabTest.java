package io.javahub.level3.garbagecollectionbasics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 3: Garbage Collection Basics.
 */
public class GarbagecollectionbasicsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        GarbagecollectionbasicsLab lab = new GarbagecollectionbasicsLab();

        assertThat(lab.getLevel()).isEqualTo(3);
        assertThat(lab.getTopicTitle()).isEqualTo("Garbage Collection Basics");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
