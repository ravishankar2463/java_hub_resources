# Level 13: System Design — Consistent Hashing & Scalability Patterns

This project provides a production-grade **Consistent Hashing Ring** implementation featuring:
- **Virtual Nodes**: Prevents hash skew and hot-spotting by mapping each physical machine to 100 virtual points on the 32-bit circular key space.
- **Minimal Rebalancing**: Adding or removing a physical server migrates only $K/N$ keys, compared to $O(K)$ catastrophic invalidation with traditional modulo hashing.

---

## Testing Routing

```bash
# Route a session key
curl -s "http://localhost:8080/api/v1/partitioning/route?key=session_98234"

# Add a fourth cluster node
curl -X POST "http://localhost:8080/api/v1/partitioning/nodes?nodeName=storage-node-apac-1"
```
