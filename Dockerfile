# ========================================================
# Multi-Stage Production Dockerfile for Java 21
# Stage 1: Build & Package with Maven Caching
# ========================================================
FROM maven:3.9.8-eclipse-temurin-21-alpine AS builder
WORKDIR /workspace/app

# Pre-fetch dependencies in a separate layer for fast re-builds
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and compile jar
COPY src src
RUN mvn clean package -DskipTests -B

# ========================================================
# Stage 2: Minimal Distroless / Hardened Alpine Runtime
# ========================================================
FROM eclipse-temurin:21-jre-alpine AS runtime

LABEL maintainer="JavaHub Engineering <team@javahub.io>"
LABEL description="Hardened Spring Boot 21 production image"

# Create unprivileged application user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /opt/app

# Copy artifact from build stage
COPY --from=builder /workspace/app/target/*.jar app.jar
RUN chown -R appuser:appgroup /opt/app

# Drop root privileges
USER appuser:appgroup

# Production JVM Ergonomics tuned for container cgroups
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:InitialRAMPercentage=50.0 \
               -XX:+ExitOnOutOfMemoryError \
               -XX:+HeapDumpOnOutOfMemoryError \
               -XX:HeapDumpPath=/tmp/oom-dump.hprof \
               -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

HEALTHCHECK --interval=15s --timeout=3s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
