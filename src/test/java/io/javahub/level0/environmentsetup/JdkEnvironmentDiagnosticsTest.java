package io.javahub.level0.environmentsetup;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JdkEnvironmentDiagnosticsTest {

    private final JdkEnvironmentDiagnostics diagnostics = new JdkEnvironmentDiagnostics();

    @Test
    @DisplayName("Should detect Java 21 or higher runtime")
    void testJavaVersion() {
        assertThat(diagnostics.isJava21OrHigher())
            .as("Java runtime feature version must be at least 21")
            .isTrue();
    }

    @Test
    @DisplayName("Should inspect heap memory and available processor cores")
    void testJvmSpecsCapture() {
        JdkEnvironmentDiagnostics.JvmSpecs specs = diagnostics.captureCurrentSpecs();

        assertThat(specs.availableProcessors()).isGreaterThan(0);
        assertThat(specs.maxHeapMemoryBytes()).isGreaterThan(0L);
        assertThat(specs.garbageCollectorNames()).isNotEmpty();
        assertThat(specs.osArch()).isNotBlank();
    }
}
