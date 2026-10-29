# Real-Time Messaging (WebSockets & SSE) Live Application

Demonstrates:
- **Server-Sent Events (SSE)**: Single-directional HTTP streaming using `SseEmitter`.
- **Fan-Out Broadcasting**: Distributing real-time price ticks to multiple active client streams.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. In Terminal A, open persistent SSE event stream:
curl -N -H "Accept: text/event-stream" http://localhost:8080/api/v1/stream/prices

# 2. In Terminal B, broadcast price updates:
curl -X POST "http://localhost:8080/api/v1/stream/broadcast?ticker=AAPL&price=230.50"
curl -X POST "http://localhost:8080/api/v1/stream/broadcast?ticker=TSLA&price=255.00"
```

## Automated Testing
```bash
mvn test
```
