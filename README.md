# Aspect-Oriented Programming (AOP) Live Spring Boot Application

Demonstrates:
- **Custom Annotations**: `@LogExecutionTime` method interceptor.
- **AspectJ `@Around` Advice**: Non-invasive performance profiling and joinpoint argument capture.
- **Audit Trail Recording**: Transparent metrics logging via dynamic proxy wrapping.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Trigger payment charge (intercepted by AOP)
curl -X POST "http://localhost:8080/api/v1/payments/charge?customerId=CUST-404&amount=199.99"

# 2. Trigger refund (intercepted by AOP)
curl -X POST "http://localhost:8080/api/v1/payments/refund?transactionId=TXN-8821"

# 3. View live recorded AOP execution audit logs
curl -X GET http://localhost:8080/api/v1/payments/audit-log
```

## Automated Testing
```bash
mvn test
```
