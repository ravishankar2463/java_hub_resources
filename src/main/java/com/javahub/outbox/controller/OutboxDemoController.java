package com.javahub.outbox.controller;

import com.javahub.outbox.entity.OutboxEvent;
import com.javahub.outbox.service.OrderOutboxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/outbox")
public class OutboxDemoController {

    private final OrderOutboxService service;

    public OutboxDemoController(OrderOutboxService service) {
        this.service = service;
    }

    @PostMapping("/orders")
    public ResponseEntity<Map<String, Object>> placeOrder(
            @RequestParam String orderId,
            @RequestParam double amount) {
        OutboxEvent event = service.createOrderWithOutbox(orderId, amount);
        return ResponseEntity.ok(Map.of(
            "orderId", orderId,
            "outboxEventId", event.getId(),
            "status", event.getStatus()
        ));
    }

    @GetMapping("/events")
    public ResponseEntity<List<OutboxEvent>> listEvents() {
        return ResponseEntity.ok(service.getAllEvents());
    }
}
