# Level 12: Helm Package Management for Kubernetes

This project includes a **production-ready Helm chart** (`chart/`) for templating, versioning, and deploying Java microservices onto any Kubernetes cluster.

---

## Helm Chart Structure

```
chart/
├── Chart.yaml             # Package metadata & semver
├── values.yaml            # Configurable environment overrides
└── templates/
    ├── _helpers.tpl       # Common Go template macros
    ├── deployment.yaml    # Replicated pod deployment
    └── service.yaml       # ClusterIP abstraction
```

---

## Helm CLI Commands

```bash
# Lint the chart for syntax & best practice errors
helm lint chart/

# Dry-run template rendering with mock values
helm template my-release chart/

# Install or upgrade chart to target cluster
helm upgrade --install javahub-app chart/ --values chart/values.yaml

# Run test suite
mvn clean test
```
