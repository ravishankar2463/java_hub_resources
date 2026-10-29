# Building RESTful APIs Live Spring Boot 3 Application

Features:
- **Clean RESTful Design**: Semantic HTTP status codes (`200 OK`, `201 Created` with `Location` header, `204 No Content`, `400 Bad Request`, `404 Not Found`).
- **RFC 7807 ProblemDetail**: Modern standardized error response specifications built into Spring 6 / Boot 3.
- **Jakarta Bean Validation**: Declarative input sanitization (`@NotBlank`, `@DecimalMin`, `@Size`).

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Get all products
curl -X GET http://localhost:8080/api/v1/products

# 2. Create valid product (returns 201 Created + Location header)
curl -i -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Wireless Ergonomic Mouse", "sku": "TECH-MSE-09", "price": 89.95}'

# 3. Test validation error (returns RFC 7807 ProblemDetail)
curl -i -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{"name": "", "sku": "", "price": -5.0}'

# 4. Test 404 Not Found error (returns RFC 7807 ProblemDetail)
curl -i -X GET http://localhost:8080/api/v1/products/prod-unknown
```

## Automated Testing
```bash
mvn test
```
