package com.javahub.jwt.controller;

import com.javahub.jwt.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        if ("admin".equals(username) && "secret".equals(password)) {
            String token = jwtService.generateToken(username, "ADMIN");
            return ResponseEntity.ok(Map.of("token", token, "tokenType", "Bearer"));
        } else if ("user".equals(username) && "secret".equals(password)) {
            String token = jwtService.generateToken(username, "USER");
            return ResponseEntity.ok(Map.of("token", token, "tokenType", "Bearer"));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Bad credentials"));
    }

    @GetMapping("/secure/data")
    public ResponseEntity<Map<String, Object>> getSecureData(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
            "message", "Secret enterprise vault unlocked with valid JWT Bearer token!",
            "user", authentication.getName(),
            "roles", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList()
        ));
    }
}
