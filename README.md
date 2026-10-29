# Stateless Authentication with JWT Live Application

Demonstrates:
- **Stateless Session Management**: `SessionCreationPolicy.STATELESS` with Spring Security.
- **HMAC-SHA256 JWT Generation**: Minting and validating signed JWT claims using `io.jsonwebtoken:jjwt`.
- **OncePerRequestFilter**: Intercepting and decoding the `Authorization: Bearer <token>` HTTP header.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Login to mint a JWT token (username=admin, password=secret)
TOKEN=$(curl -s -X POST "http://localhost:8080/api/v1/auth/login?username=admin&password=secret" | grep -o '"token":"[^"]*' | cut -d'"' -f4)
echo "JWT Token: $TOKEN"

# 2. Access protected endpoint using the Bearer token
curl -i -X GET http://localhost:8080/api/v1/secure/data \
  -H "Authorization: Bearer $TOKEN"

# 3. Verify access is rejected without the Bearer token (Returns 403 Forbidden)
curl -i -X GET http://localhost:8080/api/v1/secure/data
```

## Automated Testing
```bash
mvn test
```
