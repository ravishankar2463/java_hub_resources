package com.javahub.tools.service;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.function.Function;
@Service
public class ToolExecutionRegistryService {
    public record ToolResult(String tool, boolean success, String output) {}
    private final Map<String, Function<String, String>> tools = new HashMap<>();
    public ToolExecutionRegistryService() {
        tools.put("checkFlightStatus", flightNo -> "Flight " + flightNo + " is ON_TIME");
        tools.put("getAccountBalance", acct -> "Account " + acct + " balance: $12,450.00");
    }
    public ToolResult execute(String toolName, String arg) {
        var fn = tools.get(toolName);
        if (fn == null) return new ToolResult(toolName, false, "Unknown tool: " + toolName);
        return new ToolResult(toolName, true, fn.apply(arg));
    }
    public Set<String> getAvailableTools() { return tools.keySet(); }
}
