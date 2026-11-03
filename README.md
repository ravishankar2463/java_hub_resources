# Level 12: CI/CD Pipelines & Infrastructure as Code (IaC)

This project provides an automated **CI/CD Pipeline** with GitHub Actions and declarative **Terraform** configuration for deploying Java services.

---

## Pipeline Features

1. **Deterministic Build & Quality Gate**:
   - Automated compilation, unit testing, and static analysis verification via `mvn verify`.
2. **Container Security Audit**:
   - Vulnerability analysis preventing deployment of compromised dependencies.
3. **Terraform Validation**:
   - Automated syntax formatting and schema validation (`terraform fmt`, `terraform validate`).

---

## Verification Commands

```bash
# Run automated tests
mvn clean test

# Validate Terraform code locally
terraform -chdir=terraform init -backend=false
terraform -chdir=terraform validate
```
