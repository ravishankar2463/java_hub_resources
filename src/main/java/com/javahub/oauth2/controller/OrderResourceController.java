package com.javahub.oauth2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OrderResourceController {

    @GetMapping("/public/ping")
    public ResponseEntity<Map<String, String>> ping() {
        return ResponseEntity.ok(Map.of("status", "RESOURCE_SERVER_HEALTHY"));
    }

    @GetMapping("/orders/my-orders")
    public ResponseEntity<Map<String, Object>> getOrders(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(Map.of(
            "clientId", jwt != null ? jwt.getSubject() : "test-client",
            "orders", List.of(
                Map.of("id", "ORD-1", "amount", 199.99),
                Map.of("id", "ORD-2", "amount", 89.50)
            )
        ));
    }
}
