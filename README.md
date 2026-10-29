# Resiliency Patterns & Circuit Breakers Live Application

Run: `mvn spring-boot:run`
Trip breaker: 3 failures `curl -X POST "http://localhost:8080/api/v1/resilience/call?fail=true"`
