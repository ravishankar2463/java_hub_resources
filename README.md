# Custom Spring Boot Auto-Configurations Live Application

Demonstrates:
- **Spring Boot 3 `@AutoConfiguration`**: Modern auto-configuration entry points.
- **`AutoConfiguration.imports`**: Registered via `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.
- **Conditional Beans**: `@ConditionalOnProperty` and `@ConditionalOnMissingBean` for customizable defaults.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Query active tenant resolved from auto-configured properties
curl -X GET http://localhost:8080/api/v1/tenant/current

# 2. Switch tenant dynamically
curl -X POST "http://localhost:8080/api/v1/tenant/switch?tenantId=enterprise-apac"
```

## Automated Testing
```bash
mvn test
```
