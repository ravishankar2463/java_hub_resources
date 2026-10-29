# Spring Security Architecture Live Application

Demonstrates:
- **SecurityFilterChain**: Modern lambda-based Spring Security 6 authorization rules.
- **Role-Based Access Control (RBAC)**: Fine-grained access distinctions between `USER` and `ADMIN`.
- **BCrypt Password Encoding**: Production-grade cryptographic password storage.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Access public endpoint (No Auth)
curl -X GET http://localhost:8080/api/v1/public/welcome

# 2. Access protected profile with USER credentials (user : password123)
curl -u user:password123 http://localhost:8080/api/v1/user/profile

# 3. Access admin zone with USER credentials (Returns 403 Forbidden)
curl -u user:password123 http://localhost:8080/api/v1/admin/system-status

# 4. Access admin zone with ADMIN credentials (admin : admin123) (Returns 200 OK)
curl -u admin:admin123 http://localhost:8080/api/v1/admin/system-status
```

## Automated Testing
```bash
mvn test
```
