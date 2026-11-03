# Level 12: Kubernetes (K8s) for Java Developers

This repository contains a full production-grade **Kubernetes Orchestration** suite for Spring Boot 3, featuring Actuator probes, graceful shutdown hooks, zero-downtime rolling updates, and Horizontal Pod Autoscaling (HPA).

---

## Kubernetes Manifests Included

- `k8s/deployment.yaml`: Configures `preStop` sleep hooks (giving kube-proxy time to update iptables before SIGTERM), liveness & readiness probes, and memory requests/limits.
- `k8s/service.yaml`: ClusterIP service routing traffic only to `Ready` pods.
- `k8s/hpa.yaml`: Autoscaler targeting 75% average CPU utilization across 3 to 10 replicas.

---

## Local Verification Commands

```bash
# Deploy to local Minikube / Kind cluster
kubectl apply -f k8s/

# Verify pods running
kubectl get pods -l app=javahub-k8s-service

# Run test suite
mvn clean test
```
