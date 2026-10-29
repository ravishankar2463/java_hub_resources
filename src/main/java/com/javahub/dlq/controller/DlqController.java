package com.javahub.dlq.controller;
import com.javahub.dlq.service.DeadLetterQueueDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/kafka/dlq")
public class DlqController {
    private final DeadLetterQueueDispatcher dispatcher;
    public DlqController(DeadLetterQueueDispatcher dispatcher) { this.dispatcher = dispatcher; }
    @PostMapping("/simulate-poison-pill")
    public ResponseEntity<Map<String, String>> simulatePoisonPill(@RequestParam String payload) {
        dispatcher.routeToDlq("orders.v1", payload, "Unparseable JSON syntax error", 3);
        return ResponseEntity.ok(Map.of("status", "ROUTED_TO_DLQ", "topic", "orders.v1.DLQ"));
    }
    @GetMapping("/messages")
    public ResponseEntity<List<DeadLetterQueueDispatcher.DeadLetterEntry>> getMessages() {
        return ResponseEntity.ok(dispatcher.getDlqEntries());
    }
}
