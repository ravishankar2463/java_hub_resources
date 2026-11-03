package io.javahub.level13.jfr.controller;

import io.javahub.level13.jfr.event.PaymentProcessingEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiling")
public class ProfilingTriggerController {

    @PostMapping("/simulate-workload")
    public ResponseEntity<Map<String, Object>> simulatePayment(@RequestParam(defaultValue = "100.0") double amount) {
        String txId = UUID.randomUUID().toString();

        PaymentProcessingEvent event = new PaymentProcessingEvent();
        event.setTransactionId(txId);
        event.setAmount(amount);

        // Begin recording JFR event duration
        event.begin();
        try {
            // Simulate CPU & thread work
            Thread.sleep(15);
            event.setStatus("COMPLETED_SUCCESS");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            event.setStatus("INTERRUPTED");
        } finally {
            // Commit event directly into JVM flight recorder circular ring buffer
            event.commit();
        }

        return ResponseEntity.ok(Map.of(
                "transactionId", txId,
                "amount", amount,
                "jfrEventCommitted", true
        ));
    }
}
