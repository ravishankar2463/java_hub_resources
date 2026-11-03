# Level 13: Zero-Downtime Deployments — Blue-Green & Canary Rollouts

This project implements a dynamic traffic router demonstrating:
1. **Canary Releases**: Route a small percentage of user traffic (e.g. 5% $	o$ 20% $	o$ 50%) to the new release to validate production error rates and latency.
2. **Automated Rollback**: Instantly route 100% of traffic back to the stable Blue deployment if error budget anomalies occur.
3. **Instant Cutover**: Promote Green to 100% primary once health gates are satisfied.

---

## Verification Commands

```bash
# Test traffic route
curl -s http://localhost:8080/api/v1/deployments/route

# Set canary weight to 25%
curl -X POST "http://localhost:8080/api/v1/deployments/canary-weight?weight=25"

# Emergency rollback
curl -X POST http://localhost:8080/api/v1/deployments/rollback
```
