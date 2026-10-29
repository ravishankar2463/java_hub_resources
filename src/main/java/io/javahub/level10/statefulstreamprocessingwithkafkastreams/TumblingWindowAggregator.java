package io.javahub.level10.statefulstreamprocessingwithkafkastreams;

import java.util.HashMap;
import java.util.Map;

/**
 * Emulates Kafka Streams tumbling window aggregation.
 */
public class TumblingWindowAggregator {

    public record WindowKey(String key, long windowStartEpoch) {}

    private final Map<WindowKey, Long> windowCounts = new HashMap<>();
    private final long windowSizeMillis;

    public TumblingWindowAggregator(long windowSizeMillis) {
        this.windowSizeMillis = windowSizeMillis;
    }

    public void recordEvent(String key, long eventTimeEpoch) {
        long windowStart = (eventTimeEpoch / windowSizeMillis) * windowSizeMillis;
        WindowKey wk = new WindowKey(key, windowStart);
        windowCounts.put(wk, windowCounts.getOrDefault(wk, 0L) + 1);
    }

    public long getCount(String key, long windowStartEpoch) {
        return windowCounts.getOrDefault(new WindowKey(key, windowStartEpoch), 0L);
    }
}
