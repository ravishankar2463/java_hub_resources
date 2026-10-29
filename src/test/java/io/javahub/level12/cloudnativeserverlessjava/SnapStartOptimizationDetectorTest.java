package io.javahub.level12.cloudnativeserverlessjava;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SnapStartOptimizationDetectorTest {

    @Test
    @DisplayName("CRaC beforeCheckpoint hook should prewarm runtime before microsecond restore")
    void testSnapStart() {
        var optimizer = new SnapStartOptimizationDetector();
        assertThat(optimizer.isOptimizedForInstantRestore()).isFalse();

        optimizer.beforeCheckpoint();
        assertThat(optimizer.isOptimizedForInstantRestore()).isTrue();
    }
}
