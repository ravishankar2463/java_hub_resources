package io.javahub.level13.advancedjvmtuningprofiling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JvmGarbageCollectorProfilerTest {

    @Test
    @DisplayName("Should detect when GC pause duration exceeds configured SLA threshold")
    void testGcSla() {
        var compliant = new JvmGarbageCollectorProfiler.GcPauseReport(5, 20);
        var breached = new JvmGarbageCollectorProfiler.GcPauseReport(45, 20);

        assertThat(compliant.isSlaBreached()).isFalse();
        assertThat(breached.isSlaBreached()).isTrue();
    }
}
