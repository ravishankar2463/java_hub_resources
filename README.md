# Message Brokers vs Event Streaming Live Application

Run server: `mvn spring-boot:run`
Produce event: `curl -X POST "http://localhost:8080/api/v1/stream/produce?key=ORD-101&payload=OrderCreated"`
