package io.javahub.level13.productionmetricswithmicrometerandprometheus;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Emulates Micrometer Counter and Gauge metric tracking.
 */
public class MicrometerMetricCollector {

    private final LongAdder requestCounter = new LongAdder();
    private final AtomicLong activeConnectionsGauge = new AtomicLong(0);

    public void recordRequest() {
        requestCounter.increment();
    }

    public void connectionOpened() { activeConnectionsGauge.incrementAndGet(); }
    public void connectionClosed() { activeConnectionsGauge.decrementAndGet(); }

    public long getTotalRequests() { return requestCounter.sum(); }
    public long getActiveConnections() { return activeConnectionsGauge.get(); }
}
