package io.javahub.level13.systemdesignobservability;

import java.util.HashMap;
import java.util.Map;

/**
 * W3C TraceContext traceparent header generation and cross-service propagation.
 */
public class OpenTelemetryTracePropagator {

    public record TraceContext(String version, String traceId, String spanId, String traceFlags) {
        public String formatHeader() {
            return version + "-" + traceId + "-" + spanId + "-" + traceFlags;
        }

        public static TraceContext parseHeader(String header) {
            String[] parts = header.split("-");
            if (parts.length != 4) throw new IllegalArgumentException("Malformed traceparent header");
            return new TraceContext(parts[0], parts[1], parts[2], parts[3]);
        }
    }

    public Map<String, String> injectTracingHeaders(TraceContext context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("traceparent", context.formatHeader());
        return headers;
    }
}
