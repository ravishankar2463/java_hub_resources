# Kafka Idempotent Producer Live Application

Demonstrates idempotent message delivery and duplicate elimination using sequence numbering.

Run: `mvn spring-boot:run`
Publish: `curl -X POST "http://localhost:8080/api/v1/orders/publish?orderId=ORD-101&payload=Buy&sequence=1"`
