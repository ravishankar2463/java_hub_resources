# Level 12: Google Jib — Daemonless Container Image Builds

This project demonstrates **Google Jib**, allowing Java applications to build compliant OCI/Docker container images directly from Maven without requiring a Docker daemon installed on the CI build host.

---

## Why Jib Over Traditional Dockerfiles?

| Dimension | Dockerfile | Google Jib |
|---|---|---|
| **Docker Daemon** | Required (Needs root / socket mounting) | **Not required** (Pure Java client) |
| **Layering** | Monolithic fat JAR in 1 layer | **Split into 4 layers** (Deps, Resources, Classes) |
| **CI Cache Hits** | Rebuilding fat JAR invalidates layer | **Only modified `.class` files are pushed** (~20KB) |
| **Reproducibility** | Timestamps vary across builds | **Bit-for-bit reproducible** |

---

## Running Jib Commands

```bash
# Build image directly to local Docker daemon (if running)
mvn compile jib:dockerBuild

# Build directly to a remote registry without Docker daemon
mvn compile jib:build -Dimage=myregistry.azurecr.io/jib-service:v1

# Run automated tests
mvn clean test
```
