# Structured Output Record Parser

Demonstrates parsing structured JSON from Large Language Models directly into immutable Java 21 Records:
- **Type-safe schema binding**: Prevents hallucinated or malformed properties from breaking downstream processing.
- **Fail-safe fallbacks**: Ensures sensible defaults when optional fields are omitted.

Run test suite: `mvn clean test`
