package io.javahub.level3.jvmarchitectureinternals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 3: JVM Architecture Internals.
 */
public class JvmarchitectureinternalsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        JvmarchitectureinternalsLab lab = new JvmarchitectureinternalsLab();

        assertThat(lab.getLevel()).isEqualTo(3);
        assertThat(lab.getTopicTitle()).isEqualTo("JVM Architecture Internals");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
