package io.javahub.level10.statefulstreamprocessingwithkafkastreams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TumblingWindowAggregatorTest {

    @Test
    @DisplayName("Should aggregate events within discrete 60-second tumbling windows")
    void testTumblingWindows() {
        var aggregator = new TumblingWindowAggregator(60_000L); // 60s windows

        aggregator.recordEvent("sensor_A", 10_000L); // Window 0
        aggregator.recordEvent("sensor_A", 25_000L); // Window 0
        aggregator.recordEvent("sensor_A", 70_000L); // Window 60,000

        assertThat(aggregator.getCount("sensor_A", 0L)).isEqualTo(2L);
        assertThat(aggregator.getCount("sensor_A", 60_000L)).isEqualTo(1L);
    }
}
