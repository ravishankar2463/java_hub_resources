package io.javahub.level13.deployments.controller;

import io.javahub.level13.deployments.router.CanaryTrafficRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/deployments")
public class CanaryTrafficController {

    private final CanaryTrafficRouter router;

    public CanaryTrafficController(CanaryTrafficRouter router) {
        this.router = router;
    }

    @GetMapping("/route")
    public ResponseEntity<Map<String, Object>> routeTraffic() {
        String targetDeployment = router.routeTraffic();
        return ResponseEntity.ok(Map.of(
                "targetDeployment", targetDeployment,
                "currentCanaryWeight", router.getCanaryWeight() + "%",
                "isCutoverToGreen", router.isCutoverToGreen()
        ));
    }

    @PostMapping("/canary-weight")
    public ResponseEntity<Map<String, Object>> updateWeight(@RequestParam int weight) {
        router.setCanaryWeight(weight);
        return ResponseEntity.ok(Map.of("message", "Canary weight updated", "newWeight", weight));
    }

    @PostMapping("/rollback")
    public ResponseEntity<Map<String, String>> rollback() {
        router.emergencyRollback();
        return ResponseEntity.ok(Map.of("message", "Emergency rollback executed: 100% traffic to BLUE_V1"));
    }
}
