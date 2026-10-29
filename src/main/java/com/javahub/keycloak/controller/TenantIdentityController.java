package com.javahub.keycloak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/identity")
public class TenantIdentityController {

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> whoami(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.ok(Map.of("status", "ANONYMOUS_ACCESS"));
        }
        return ResponseEntity.ok(Map.of(
            "username", auth.getName(),
            "roles", auth.getAuthorities().stream().map(GrantedAuthority -> GrantedAuthority.getAuthority()).toList(),
            "realm", "javahub-enterprise"
        ));
    }
}
