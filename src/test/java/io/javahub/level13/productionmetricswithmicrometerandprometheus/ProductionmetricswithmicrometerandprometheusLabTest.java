package io.javahub.level13.productionmetricswithmicrometerandprometheus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 13: Production Metrics with Micrometer and Prometheus.
 */
public class ProductionmetricswithmicrometerandprometheusLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        ProductionmetricswithmicrometerandprometheusLab lab = new ProductionmetricswithmicrometerandprometheusLab();

        assertThat(lab.getLevel()).isEqualTo(13);
        assertThat(lab.getTopicTitle()).isEqualTo("Production Metrics with Micrometer and Prometheus");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
