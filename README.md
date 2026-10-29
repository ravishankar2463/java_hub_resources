# Build Tools & Maven Lifecycle Live Application

This full Spring Boot 3 application demonstrates modern Maven configuration, reproducible builds, and build metadata injection.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing Endpoints
```bash
# Query build and runtime metadata
curl -X GET http://localhost:8080/api/v1/build/info
```

## Running the Test Suite
```bash
mvn test
```
