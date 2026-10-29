package io.javahub.level13.jvmprofilingwithjfrandasyncprofiler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 13: JVM Profiling with JFR and Async-Profiler.
 */
public class JvmprofilingwithjfrandasyncprofilerLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        JvmprofilingwithjfrandasyncprofilerLab lab = new JvmprofilingwithjfrandasyncprofilerLab();

        assertThat(lab.getLevel()).isEqualTo(13);
        assertThat(lab.getTopicTitle()).isEqualTo("JVM Profiling with JFR and Async-Profiler");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
