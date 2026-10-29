package com.javahub.producer.controller;
import com.javahub.producer.service.IdempotentOrderProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/orders/publish")
public class OrderProducerController {
    private final IdempotentOrderProducerService producerService;
    public OrderProducerController(IdempotentOrderProducerService producerService) { this.producerService = producerService;
    }
    @PostMapping
    public ResponseEntity<IdempotentOrderProducerService.DispatchResult> publish(
            @RequestParam String orderId, @RequestParam String payload, @RequestParam(defaultValue = "1") int sequence) {
        return ResponseEntity.ok(producerService.dispatchOrder(orderId, payload, sequence));
    }
}
