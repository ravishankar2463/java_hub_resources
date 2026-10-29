package com.javahub.temporal.controller;
import com.javahub.temporal.service.WorkflowEventHistoryEngine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/temporal/workflows")
public class WorkflowController {
    private final WorkflowEventHistoryEngine engine;
    public WorkflowController(WorkflowEventHistoryEngine engine) { this.engine = engine; }
    @PostMapping("/start")
    public ResponseEntity<WorkflowEventHistoryEngine.WorkflowHistory> start(@RequestParam String id) {
        return ResponseEntity.ok(engine.startWorkflow(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<WorkflowEventHistoryEngine.WorkflowHistory> get(@PathVariable String id) {
        var w = engine.getWorkflow(id);
        return w != null ? ResponseEntity.ok(w) : ResponseEntity.notFound().build();
    }
}
