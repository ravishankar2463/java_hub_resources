package io.javahub.level13.systemdesignobservability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OpenTelemetryTracePropagatorTest {

    @Test
    @DisplayName("Should format and parse W3C traceparent header adhering to OpenTelemetry standards")
    void testTraceContext() {
        var propagator = new OpenTelemetryTracePropagator();
        var ctx = new OpenTelemetryTracePropagator.TraceContext(
            "00", "4bf92f3577b34da6a3ce929d0e0e4736", "00f067aa0ba902b7", "01"
        );

        var headers = propagator.injectTracingHeaders(ctx);
        assertThat(headers.get("traceparent")).isEqualTo("00-4bf92f3577b34da6a3ce929d0e0e4736-00f067aa0ba902b7-01");

        var parsed = OpenTelemetryTracePropagator.TraceContext.parseHeader(headers.get("traceparent"));
        assertThat(parsed.traceId()).isEqualTo("4bf92f3577b34da6a3ce929d0e0e4736");
    }
}
