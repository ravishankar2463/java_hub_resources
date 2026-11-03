package io.javahub.level13.observability.controller;

import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/observability")
public class TraceContextController {

    private static final Logger log = LoggerFactory.getLogger(TraceContextController.class);
    private final Tracer tracer;

    public TraceContextController(Tracer tracer) {
        this.tracer = tracer;
    }

    @GetMapping("/trace")
    public ResponseEntity<Map<String, String>> getCurrentTrace() {
        var currentSpan = tracer.currentSpan();
        String traceId = currentSpan != null ? currentSpan.context().traceId() : "none";
        String spanId = currentSpan != null ? currentSpan.context().spanId() : "none";

        log.info("Processing request within active OpenTelemetry context: traceId={}, spanId={}", traceId, spanId);

        return ResponseEntity.ok(Map.of(
                "traceId", traceId,
                "spanId", spanId,
                "w3cStandard", "traceparent: 00-" + traceId + "-" + spanId + "-01"
        ));
    }
}
