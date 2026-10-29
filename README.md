# OAuth2 & OIDC Resource Server Live Application

Demonstrates:
- **Spring Security OAuth2 Resource Server**: Bearer token validation and decoding.
- **Custom JWT Claim Mapping**: Converting OAuth2 scopes into Spring `GrantedAuthority` instances.
- **Scope-based Access Control**: Restricting API endpoints by required scope (`SCOPE_orders.read`).

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Access public health endpoint
curl -X GET http://localhost:8080/api/v1/public/ping
```

## Automated Testing
```bash
mvn test
```
