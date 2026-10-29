# Centralized Identity with Keycloak Live Application

Demonstrates:
- **Keycloak Role Claims Mapping**: Parsing nested `realm_access.roles` and `resource_access` claims from OpenID Connect ID tokens.
- **Custom JWT Converter**: Translating enterprise identity provider roles into Spring Security authorities.

## Running the Application
```bash
mvn spring-boot:run
```

## Automated Testing
```bash
mvn test
```
