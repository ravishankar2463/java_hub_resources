package com.javahub.resilience.controller;
import com.javahub.resilience.service.CircuitBreakerStateMachine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/resilience")
public class ResilienceController {
    private final CircuitBreakerStateMachine circuitBreaker;
    public ResilienceController(CircuitBreakerStateMachine circuitBreaker) { this.circuitBreaker = circuitBreaker; }
    @PostMapping("/call")
    public ResponseEntity<Map<String, Object>> call(@RequestParam(defaultValue = "false") boolean fail) {
        String result = circuitBreaker.execute(fail);
        return ResponseEntity.ok(Map.of("state", circuitBreaker.getState().toString(), "result", result));
    }
    @PostMapping("/reset")
    public ResponseEntity<Map<String, String>> reset() {
        circuitBreaker.reset();
        return ResponseEntity.ok(Map.of("status", "CIRCUIT_BREAKER_RESET"));
    }
}
