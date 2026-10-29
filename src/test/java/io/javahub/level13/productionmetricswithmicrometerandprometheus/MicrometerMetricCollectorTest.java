package io.javahub.level13.productionmetricswithmicrometerandprometheus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MicrometerMetricCollectorTest {

    @Test
    @DisplayName("Metric collector should record throughput counters and active connection gauges")
    void testMetrics() {
        var collector = new MicrometerMetricCollector();

        collector.recordRequest();
        collector.recordRequest();
        collector.connectionOpened();

        assertThat(collector.getTotalRequests()).isEqualTo(2L);
        assertThat(collector.getActiveConnections()).isEqualTo(1L);

        collector.connectionClosed();
        assertThat(collector.getActiveConnections()).isEqualTo(0L);
    }
}
