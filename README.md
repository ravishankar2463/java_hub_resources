# Kafka Consumer Group Rebalance Live Application

Simulates consumer join/leave events and automatic partition reassignment across members.

Run: `mvn spring-boot:run`
Join: `curl -X POST "http://localhost:8080/api/v1/kafka/group/join?memberId=instance-1"`
