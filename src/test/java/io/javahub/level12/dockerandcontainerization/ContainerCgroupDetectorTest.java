package io.javahub.level12.dockerandcontainerization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ContainerCgroupDetectorTest {

    @Test
    @DisplayName("JVM should detect available cores and heap memory constraints")
    void testContainerDetection() {
        var detector = new ContainerCgroupDetector();
        var spec = detector.getEffectiveRuntimeLimits();

        assertThat(spec.availableProcessors()).isGreaterThan(0);
        assertThat(spec.maxHeapBytes()).isGreaterThan(0L);
    }
}
