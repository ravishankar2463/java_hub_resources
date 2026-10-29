package com.javahub.aop.controller;

import com.javahub.aop.aspect.ExecutionTimeLoggingAspect;
import com.javahub.aop.service.PaymentGatewayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentGatewayService paymentService;
    private final ExecutionTimeLoggingAspect loggingAspect;

    public PaymentController(PaymentGatewayService paymentService, ExecutionTimeLoggingAspect loggingAspect) {
        this.paymentService = paymentService;
        this.loggingAspect = loggingAspect;
    }

    @PostMapping("/charge")
    public ResponseEntity<Map<String, Object>> charge(@RequestParam String customerId, @RequestParam double amount) throws Exception {
        String confirmation = paymentService.chargeCreditCard(customerId, amount);
        return ResponseEntity.ok(Map.of("customerId", customerId, "confirmation", confirmation, "status", "SUCCESS"));
    }

    @PostMapping("/refund")
    public ResponseEntity<Map<String, Object>> refund(@RequestParam String transactionId) throws Exception {
        String result = paymentService.refund(transactionId);
        return ResponseEntity.ok(Map.of("transactionId", transactionId, "result", result, "status", "SUCCESS"));
    }

    @GetMapping("/audit-log")
    public ResponseEntity<List<String>> getAuditLog() {
        return ResponseEntity.ok(loggingAspect.getAuditTrail().stream().toList());
    }
}
