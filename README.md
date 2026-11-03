# Level 13: System Design — Distributed Tracing with OpenTelemetry

This project demonstrates distributed tracing and **W3C TraceContext** (`traceparent`) propagation across asynchronous service boundaries.

---

## W3C TraceContext Specification

The `traceparent` header format:
```
version-trace_id-parent_id-trace_flags
00-4bf92f3577b34da6a3ce929d0e0e4736-00f067aa0ba902b7-01
```

---

## Verification

```bash
# Trigger traced request
curl -s http://localhost:8080/api/v1/observability/trace

# Run test suite
mvn clean test
```
