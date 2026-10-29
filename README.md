# Apache Kafka Internals Live Application

Inspects partition ISR, high watermark, and replication leader assignments.

Run: `mvn spring-boot:run`
Query: `curl -X GET http://localhost:8080/api/v1/kafka/internals/partitions`
