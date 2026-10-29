package com.javahub.streaming.controller;
import com.javahub.streaming.service.LogRetentionStreamSimulator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/stream")
public class StreamLogController {
    private final LogRetentionStreamSimulator simulator;
    public StreamLogController(LogRetentionStreamSimulator simulator) { this.simulator = simulator; }
    @PostMapping("/produce")
    public ResponseEntity<LogRetentionStreamSimulator.PartitionOffset> produce(@RequestParam String key, @RequestParam String payload) {
        return ResponseEntity.ok(simulator.append(key, payload));
    }
    @GetMapping("/consume")
    public ResponseEntity<List<LogRetentionStreamSimulator.PartitionOffset>> consume(@RequestParam(defaultValue = "0") int partition, @RequestParam(defaultValue = "0") long offset, @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(simulator.readFromOffset(partition, offset, limit));
    }
}
