# Level 15: Project Panama — Foreign Function & Memory (FFM) API

Java 21 officially stabilizes **JEP 454 (Foreign Function & Memory API)**, replacing fragile and unsafe JNI and `sun.misc.Unsafe` with safe, deterministic off-heap memory abstractions:
- **Arena**: Controls native memory allocation lifecycles (`ofConfined()`, `ofShared()`, `global()`).
- **MemorySegment**: Bounded, type-safe contiguous buffer representing off-heap memory.

---

## Run & Verify

```bash
# Run CLI demo
mvn compile exec:java

# Run test suite
mvn test
```
