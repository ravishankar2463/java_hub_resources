package io.javahub.level13.scalability.controller;

import io.javahub.level13.scalability.router.ConsistentHashRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/partitioning")
public class PartitioningController {

    private final ConsistentHashRouter router;

    public PartitioningController(ConsistentHashRouter router) {
        this.router = router;
    }

    @GetMapping("/route")
    public ResponseEntity<Map<String, Object>> route(@RequestParam String key) {
        String targetNode = router.routeKey(key);
        return ResponseEntity.ok(Map.of(
                "key", key,
                "assignedNode", targetNode,
                "totalPhysicalNodes", router.getPhysicalNodes().size(),
                "totalVirtualNodes", router.getVirtualNodeCount()
        ));
    }

    @PostMapping("/nodes")
    public ResponseEntity<Map<String, Object>> addNode(@RequestParam String nodeName) {
        router.addNode(nodeName);
        return ResponseEntity.ok(Map.of("message", "Node added to hash ring", "node", nodeName));
    }
}
