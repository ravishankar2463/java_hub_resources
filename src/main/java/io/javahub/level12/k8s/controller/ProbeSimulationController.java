package io.javahub.level12.k8s.controller;

import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/k8s")
public class ProbeSimulationController {

    private final ApplicationAvailability availability;
    private final ApplicationEventPublisher eventPublisher;

    public ProbeSimulationController(ApplicationAvailability availability, ApplicationEventPublisher eventPublisher) {
        this.availability = availability;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        return ResponseEntity.ok(Map.of(
                "liveness", availability.getLivenessState().toString(),
                "readiness", availability.getReadinessState().toString()
        ));
    }

    @PostMapping("/simulate-traffic-drain")
    public ResponseEntity<Map<String, String>> simulateTrafficDrain() {
        // Mark application as refusing traffic (takes pod out of K8s Service endpoint slice)
        AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.REFUSING_TRAFFIC);
        return ResponseEntity.ok(Map.of("message", "Pod marked as REFUSING_TRAFFIC. Kubernetes service will drain connections."));
    }

    @PostMapping("/simulate-traffic-accept")
    public ResponseEntity<Map<String, String>> simulateTrafficAccept() {
        AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.ACCEPTING_TRAFFIC);
        return ResponseEntity.ok(Map.of("message", "Pod marked as ACCEPTING_TRAFFIC. Pod will receive traffic."));
    }
}
