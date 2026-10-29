# Message Brokers vs Event Streaming Live Application

Demonstrates:
- **Append-Only Distributed Commit Log**: Preserving events permanently rather than destructive queue consumption.
- **Partitioning by Key**: Preserving deterministic ordering per partition.
- **Consumer Offset Replayability**: Reprocessing events from arbitrary past offsets.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Produce event to partitioned log
curl -X POST "http://localhost:8080/api/v1/stream/produce?key=ORD-101&payload=OrderCreated"

# 2. Consume events from partition offset 0
curl -X GET "http://localhost:8080/api/v1/stream/consume?partition=1&offset=0"
```

## Automated Testing
```bash
mvn test
```
