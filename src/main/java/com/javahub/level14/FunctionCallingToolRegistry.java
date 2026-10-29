package com.javahub.level14;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Enterprise AI: LLM Tool Calling & Function Execution Dispatcher.
 * Demonstrates registering typed functions, parsing LLM function call arguments,
 * and returning execution results to the LLM agent loop.
 */
public class FunctionCallingToolRegistry {

    public record ToolDefinition(String name, String description, String parameterSchema) {}

    public record ToolExecutionResult(String toolName, boolean success, String output) {}

    private final Map<String, ToolDefinition> tools = new HashMap<>();
    private final Map<String, Function<Map<String, String>, String>> handlers = new HashMap<>();

    public synchronized void registerTool(String name, String description, String schema, Function<Map<String, String>, String> handler) {
        tools.put(name, new ToolDefinition(name, description, schema));
        handlers.put(name, handler);
    }

    public synchronized ToolExecutionResult execute(String toolName, Map<String, String> arguments) {
        Function<Map<String, String>, String> handler = handlers.get(toolName);
        if (handler == null) {
            return new ToolExecutionResult(toolName, false, "Unknown tool: " + toolName);
        }
        try {
            String output = handler.apply(arguments);
            return new ToolExecutionResult(toolName, true, output);
        } catch (Exception ex) {
            return new ToolExecutionResult(toolName, false, "Execution error: " + ex.getMessage());
        }
    }

    public synchronized Map<String, ToolDefinition> getRegisteredTools() {
        return Map.copyOf(tools);
    }
}
