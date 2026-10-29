# Transactional Outbox Pattern Live Application

Demonstrates:
- **Dual-Write Hazard Mitigation**: Preventing inconsistencies between database commits and message broker dispatch.
- **Outbox Relay**: Asynchronously publishing events to Kafka without blocking user transactions.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Place order (creates outbox event atomically)
curl -X POST "http://localhost:8080/api/v1/outbox/orders?orderId=ORD-7711&amount=899.00"

# 2. Inspect outbox events (transitions from PENDING to PUBLISHED via scheduled relay)
curl -X GET http://localhost:8080/api/v1/outbox/events
```

## Automated Testing
```bash
mvn test
```
