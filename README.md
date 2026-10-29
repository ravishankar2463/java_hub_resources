# Chronicle Memory-Mapped Queue Simulator

Demonstrates memory-mapped zero-copy messaging architectures (Chronicle Queue / Aeron IPC):
- **Length-prefixed binary wire frames**: Fast, compact serializations.
- **Tailer Cursor Mechanics**: Sequential append-only streaming with zero OS syscalls on the reader thread.

Run test suite: `mvn clean test`
