package com.javahub.lock.controller;
import com.javahub.lock.service.DistributedLockSimulatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/lock")
public class LockDemoController {
    private final DistributedLockSimulatorService lockService;
    public LockDemoController(DistributedLockSimulatorService lockService) { this.lockService = lockService; }
    @PostMapping("/acquire")
    public ResponseEntity<Map<String, Object>> acquire(@RequestParam String resource, @RequestParam String owner) {
        boolean acquired = lockService.acquireLock(resource, owner);
        return ResponseEntity.ok(Map.of("resource", resource, "acquired", acquired));
    }
    @PostMapping("/release")
    public ResponseEntity<Map<String, Object>> release(@RequestParam String resource, @RequestParam String owner) {
        boolean released = lockService.releaseLock(resource, owner);
        return ResponseEntity.ok(Map.of("resource", resource, "released", released));
    }
}
