package com.javahub.saga.controller;
import com.javahub.saga.service.SagaOrchestratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/saga")
public class SagaController {
    private final SagaOrchestratorService orchestrator;
    public SagaController(SagaOrchestratorService orchestrator) { this.orchestrator = orchestrator; }
    @PostMapping("/orders/execute")
    public ResponseEntity<SagaOrchestratorService.SagaExecutionResult> executeSaga(
            @RequestParam String orderId, @RequestParam(defaultValue = "false") boolean failPayment) {
        return ResponseEntity.ok(orchestrator.executeOrderSaga(orderId, failPayment));
    }
}
