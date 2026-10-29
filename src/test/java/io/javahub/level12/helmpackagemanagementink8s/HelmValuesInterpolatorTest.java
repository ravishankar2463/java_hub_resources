package io.javahub.level12.helmpackagemanagementink8s;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelmValuesInterpolatorTest {

    @Test
    @DisplayName("Should interpolate Helm parameters into deployment manifest template")
    void testHelmTemplate() {
        var helm = new HelmValuesInterpolator();
        String yaml = helm.renderDeploymentYaml("v2.1.0", 3);

        assertThat(yaml).contains("replicas: 3");
        assertThat(yaml).contains("image: javahub/app:v2.1.0");
    }
}
