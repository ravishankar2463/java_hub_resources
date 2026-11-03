# Level 12: Docker and Containerization for Java Enterprise

Welcome to the **Docker and Containerization** reference implementation. This project demonstrates production-grade Java 21 container packaging using multi-stage Docker builds, cgroup memory alignment, and unprivileged user execution.

---

## Key Container Architectural Features

1. **Multi-Stage Build Pipeline**:
   - `Stage 1 (builder)`: Uses `maven:3.9.8-eclipse-temurin-21-alpine` to pre-fetch dependencies (`mvn dependency:go-offline`) before copying source code, taking full advantage of Docker layer caching.
   - `Stage 2 (runtime)`: Uses minimal `eclipse-temurin:21-jre-alpine` containing only the JRE, keeping image size below ~180MB.
2. **Container Security & Least Privilege**:
   - Creates a non-root user `appuser:appgroup`.
   - Never runs Java processes as root inside the container.
3. **Container-Aware JVM Ergonomics**:
   - Configures `-XX:+UseContainerSupport` and `-XX:MaxRAMPercentage=75.0` to prevent kernel Out-Of-Memory (OOM) killer terminations.

---

## Quickstart & Verification

### Build & Run Container
```bash
# Build the production image
docker build -t javahub-docker-app .

# Run with 512MB RAM and 1.5 CPU limit
docker run -d --name javahub-app -p 8080:8080 --memory=512m --cpus=1.5 javahub-docker-app

# Inspect live container memory diagnostics
curl -s http://localhost:8080/api/v1/container/diagnostics
```

### Run Automated Test Suite
```bash
mvn clean test
```
