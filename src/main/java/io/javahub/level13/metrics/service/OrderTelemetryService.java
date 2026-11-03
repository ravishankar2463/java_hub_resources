package io.javahub.level13.metrics.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderTelemetryService {

    private final Counter orderSuccessCounter;
    private final Counter orderFailureCounter;
    private final Timer orderProcessingTimer;
    private final AtomicInteger pendingOrdersGauge;

    public OrderTelemetryService(MeterRegistry registry) {
        this.orderSuccessCounter = Counter.builder("business.orders.placed")
                .tag("status", "success")
                .description("Total successfully completed orders")
                .register(registry);

        this.orderFailureCounter = Counter.builder("business.orders.placed")
                .tag("status", "failed")
                .description("Total failed orders")
                .register(registry);

        this.orderProcessingTimer = Timer.builder("business.orders.latency")
                .description("Latency distribution of checkout operations")
                .publishPercentiles(0.50, 0.95, 0.99)
                .register(registry);

        this.pendingOrdersGauge = registry.gauge("business.orders.pending.queue", new AtomicInteger(0));
    }

    public void recordOrder(boolean success, long durationMs) {
        if (success) {
            orderSuccessCounter.increment();
        } else {
            orderFailureCounter.increment();
        }
        orderProcessingTimer.record(Duration.ofMillis(durationMs));
    }

    public void updatePendingQueue(int count) {
        pendingOrdersGauge.set(count);
    }

    public double getSuccessCount() {
        return orderSuccessCounter.count();
    }
}
