package io.javahub.level12.kubernetesk8sforjavadevs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class K8sHealthProbeManagerTest {

    @Test
    @DisplayName("Readiness probe should fail when database connection drops without killing pod")
    void testK8sProbes() {
        var probes = new K8sHealthProbeManager();
        probes.completeStartup();

        assertThat(probes.isLivenessProbeHealthy()).isTrue();
        assertThat(probes.isReadinessProbeHealthy()).isTrue();

        probes.setDbReachable(false);
        assertThat(probes.isLivenessProbeHealthy()).isTrue(); // Pod NOT killed
        assertThat(probes.isReadinessProbeHealthy()).isFalse(); // Traffic unrouted
    }
}
