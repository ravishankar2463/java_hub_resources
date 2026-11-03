# Level 13: Production Metrics with Micrometer & Prometheus

This project configures **Micrometer** meters (Counters, Gauges, Timers with p95/p99 percentiles) and exposes them formatted for Prometheus scraping at `/actuator/prometheus`.

---

## Testing Prometheus Scraping

```bash
# Trigger business orders to populate metrics
curl -X POST "http://localhost:8080/api/v1/telemetry/orders?success=true&durationMs=25"

# Scrape metrics formatted in OpenMetrics / Prometheus notation
curl -s http://localhost:8080/actuator/prometheus | grep "business_orders"
```
