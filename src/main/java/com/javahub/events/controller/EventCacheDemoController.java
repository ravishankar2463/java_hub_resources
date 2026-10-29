package com.javahub.events.controller;

import com.javahub.events.event.OrderCreatedEvent;
import com.javahub.events.listener.OrderNotificationListener;
import com.javahub.events.service.ProductPriceCacheService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demos")
public class EventCacheDemoController {

    private final ApplicationEventPublisher publisher;
    private final OrderNotificationListener listener;
    private final ProductPriceCacheService cacheService;

    public EventCacheDemoController(
            ApplicationEventPublisher publisher,
            OrderNotificationListener listener,
            ProductPriceCacheService cacheService) {
        this.publisher = publisher;
        this.listener = listener;
        this.cacheService = cacheService;
    }

    @PostMapping("/events/publish")
    public ResponseEntity<Map<String, String>> triggerOrderEvent(
            @RequestParam String orderId,
            @RequestParam String email,
            @RequestParam double amount) {
        publisher.publishEvent(new OrderCreatedEvent(orderId, email, amount));
        return ResponseEntity.ok(Map.of("status", "EVENT_PUBLISHED", "orderId", orderId));
    }

    @GetMapping("/events/log")
    public ResponseEntity<List<String>> getEventLog() {
        return ResponseEntity.ok(listener.getEventLog().stream().toList());
    }

    @GetMapping("/cache/price")
    public ResponseEntity<Map<String, Object>> getPrice(@RequestParam String sku) {
        double price = cacheService.getPriceForSku(sku);
        return ResponseEntity.ok(Map.of(
            "sku", sku,
            "price", price,
            "dbHitCount", cacheService.getQueryCount()
        ));
    }

    @PostMapping("/cache/evict")
    public ResponseEntity<Map<String, String>> evictCache() {
        cacheService.evictAllPrices();
        return ResponseEntity.ok(Map.of("status", "CACHE_EVICTED"));
    }
}
