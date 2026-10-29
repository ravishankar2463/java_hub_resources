# LMAX Disruptor Lock-Free Ring Buffer

Demonstrates lock-free circular buffer mechanics:
- **Bitwise Masking**: Fast modulo operation `(sequence & indexMask)` leveraging power-of-two sizing.
- **Pre-allocated Event Slots**: Eliminates dynamic memory churn on critical low-latency hot paths.

Run test suite: `mvn clean test`
