package com.javahub.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SecurityDemoController {

    @GetMapping("/public/welcome")
    public ResponseEntity<Map<String, String>> publicWelcome() {
        return ResponseEntity.ok(Map.of(
            "access", "ANONYMOUS",
            "message", "Welcome! This endpoint is publicly accessible without credentials."
        ));
    }

    @GetMapping("/user/profile")
    public ResponseEntity<Map<String, Object>> userProfile(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
            "user", authentication.getName(),
            "authorities", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList(),
            "status", "AUTHENTICATED"
        ));
    }

    @GetMapping("/admin/system-status")
    public ResponseEntity<Map<String, String>> adminEndpoint(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
            "status", "CONFIDENTIAL_ADMIN_ZONE",
            "operator", authentication.getName()
        ));
    }
}
