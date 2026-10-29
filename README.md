# False Sharing Cache Line Benchmark

Demonstrates mechanical sympathy and CPU architecture alignment:
- **64-Byte Cache Lines**: How modern x86/ARM CPUs fetch memory blocks.
- **Cache Coherence Protocols (MESI)**: Why multi-core contention on adjacent fields causes pipeline stalls.
- **Defensive Padding**: Eliminating false sharing without native JNI code.

Run test suite: `mvn clean test`
