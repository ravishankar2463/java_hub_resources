# Resilient Event Processing and Dead Letter Queues Live Lab

Run: `mvn spring-boot:run`
Poison pill: `curl -X POST "http://localhost:8080/api/v1/kafka/dlq/simulate-poison-pill?payload=CORRUPT_BYTES"`
