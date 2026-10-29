# Function Calling Tool Registry

Demonstrates registering deterministic Java tools for invocation by autonomous LLMs:
- **Schema Contracts**: Exposing typed parameter schemas to the model.
- **Sandboxed Dispatch**: Safely catching execution failures and piping error messages back to the LLM agent.

Run test suite: `mvn clean test`
