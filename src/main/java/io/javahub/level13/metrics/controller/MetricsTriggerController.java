package io.javahub.level13.metrics.controller;

import io.javahub.level13.metrics.service.OrderTelemetryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/telemetry")
public class MetricsTriggerController {

    private final OrderTelemetryService telemetryService;

    public MetricsTriggerController(OrderTelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Map<String, Object>> placeOrder(@RequestParam(defaultValue = "true") boolean success,
                                                          @RequestParam(defaultValue = "45") long durationMs) {
        telemetryService.recordOrder(success, durationMs);
        return ResponseEntity.ok(Map.of(
                "status", success ? "SUCCESS" : "FAILED",
                "recordedLatencyMs", durationMs,
                "totalSuccessfulOrders", telemetryService.getSuccessCount()
        ));
    }
}
