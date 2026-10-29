package com.javahub.tools.controller;
import com.javahub.tools.service.ToolExecutionRegistryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
@RestController
@RequestMapping("/api/v1/tools")
public class ToolExecutionController {
    private final ToolExecutionRegistryService registry;
    public ToolExecutionController(ToolExecutionRegistryService registry) { this.registry = registry; }
    @GetMapping
    public ResponseEntity<Set<String>> listTools() { return ResponseEntity.ok(registry.getAvailableTools()); }
    @PostMapping("/dispatch")
    public ResponseEntity<ToolExecutionRegistryService.ToolResult> dispatch(
            @RequestParam String tool, @RequestParam String argument) {
        return ResponseEntity.ok(registry.execute(tool, argument));
    }
}
