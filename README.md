# Spring Core Internals & IoC Container Live Application

A complete Spring Boot 3 live application demonstrating:
- **ApplicationContext & Bean Lifecycle**: Inspection endpoints showing loaded bean definitions and memory.
- **BeanPostProcessor**: Dynamic lifecycle interception before and after bean initialization.
- **Prototype-in-Singleton Solution**: Dynamic prototype resolution via `ObjectProvider<T>`.

## Running the Live Server
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Inspect IoC container statistics
curl -X GET http://localhost:8080/api/v1/container/info

# 2. Filter registered Spring beans
curl -X GET "http://localhost:8080/api/v1/container/beans?filter=service"

# 3. Process order through lifecycle-managed service
curl -X POST "http://localhost:8080/api/v1/container/orders/process?orderId=ORD-7741"

# 4. Verify prototype-in-singleton dynamic instance resolution
curl -X GET http://localhost:8080/api/v1/container/reports/prototype-demo
```

## Automated Testing
```bash
mvn test
```
