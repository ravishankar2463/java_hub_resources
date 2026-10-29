# Automated Testing & Slices Live Spring Boot Application

Demonstrates:
- **Unit Testing**: Fast, isolated testing of business logic without Spring context.
- **Slice Testing with `@WebMvcTest`**: Focused controller testing with `@MockBean`.
- **Full Integration Testing with `@SpringBootTest`**: Verifying end-to-end container integration.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# Calculate sales tax
curl -X GET "http://localhost:8080/api/v1/tax/calculate?amount=150.00&rate=0.15"
```

## Running the Multi-Tier Test Suite
```bash
mvn test
```
