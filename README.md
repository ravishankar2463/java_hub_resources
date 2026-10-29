# Distributed Transactions (Saga Orchestration) Live Application

Run: `mvn spring-boot:run`
Simulate rollback: `curl -X POST "http://localhost:8080/api/v1/saga/orders/execute?orderId=SAGA-99&failPayment=true"`
