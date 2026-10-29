# Spring Events, Caching, & Scheduling Live Application

Features:
- **ApplicationEventPublisher & @EventListener**: Synchronous and `@Async` decoupled event dispatching.
- **Spring @Cacheable & @CacheEvict**: Eliminating redundant database queries via memory caching.
- **Declarative Scheduling**: `@Scheduled` background maintenance loops.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Fetch item price (First time: CACHE MISS, dbHitCount = 1)
curl -X GET "http://localhost:8080/api/v1/demos/cache/price?sku=MACBOOK-M3"

# 2. Fetch item price again (Second time: CACHE HIT, dbHitCount remains 1)
curl -X GET "http://localhost:8080/api/v1/demos/cache/price?sku=MACBOOK-M3"

# 3. Evict cache
curl -X POST http://localhost:8080/api/v1/demos/cache/evict

# 4. Publish decoupled event
curl -X POST "http://localhost:8080/api/v1/demos/events/publish?orderId=ORD-505&email=user@example.com&amount=299.00"

# 5. View logged events
curl -X GET http://localhost:8080/api/v1/demos/events/log
```

## Automated Testing
```bash
mvn test
```
