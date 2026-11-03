# Level 15: The LMAX Disruptor — Lock-Free Ring Buffers

This project demonstrates the core architectural patterns behind the **LMAX Disruptor**:
1. **Power-of-Two Bitwise Masking**: `index = sequence & (capacity - 1)` instead of CPU-costly `sequence % capacity`.
2. **Pre-Allocated Memory**: Eliminates GC pauses during steady-state event handling.

---

## Verification

```bash
mvn compile exec:java
mvn test
```
