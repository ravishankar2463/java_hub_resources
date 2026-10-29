# Garbage-Free Byte Buffer Pool

Demonstrates steady-state zero-allocation programming patterns:
- **Direct ByteBuffers**: Off-heap I/O buffers avoiding JVM garbage collector pauses.
- **Object Recycling**: Eliminating the creation of transient objects on microsecond execution paths.

Run test suite: `mvn clean test`
