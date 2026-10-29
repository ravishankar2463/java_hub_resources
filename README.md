# Project Panama Off-Heap Memory Manager

Demonstrates Java 21 standard Foreign Function & Memory API (JEP 454):
- **Arena Lifecycle**: Deterministic `Arena.ofConfined()` scoped allocation.
- **Off-heap MemorySegments**: Direct unmanaged native buffer manipulation bypassing JVM garbage collection.
- **ValueLayout**: Strongly-typed primitive addressing (`JAVA_LONG`, `JAVA_BYTE`).

Run test suite: `mvn clean test`
