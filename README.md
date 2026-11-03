# Level 12: Cloud Native & Serverless Java

This project demonstrates how modern Java eliminates the "Cold Start" penalty in serverless cloud environments (such as AWS Lambda SnapStart) using **CRaC (Coordinated Restore at Checkpoint)** and **Spring Cloud Function**.

---

## Architectural Comparison

| Technology | Typical Startup Time | Memory Footprint | JIT Peak Performance |
|---|---|---|---|
| **Standard HotSpot JVM** | 2,500ms – 5,000ms | 250MB – 400MB | Maximum (Tier 4 C2 Compiler) |
| **GraalVM Native Image** | 15ms – 30ms | 40MB – 80MB | Good (AOT, No runtime profiling) |
| **AWS SnapStart / CRaC** | **< 20ms** | 120MB | **Maximum (Pre-warmed C2 JIT bytecode)** |

---

## Testing Serverless Execution

```bash
# Run automated tests
mvn clean test

# Execute functional endpoint
curl -X POST http://localhost:8080/processOrderFunction \
  -H "Content-Type: application/json" \
  -d '{"customerId":"CUST-100","amount":99.50}'
```
