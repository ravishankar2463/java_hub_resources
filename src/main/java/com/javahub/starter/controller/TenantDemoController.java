package com.javahub.starter.controller;

import com.javahub.starter.service.TenantContextService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tenant")
public class TenantDemoController {

    private final TenantContextService tenantService;

    public TenantDemoController(TenantContextService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/current")
    public ResponseEntity<Map<String, String>> getCurrentTenant() {
        return ResponseEntity.ok(Map.of(
            "tenantId", tenantService.resolveCurrentTenant(),
            "isolationMode", tenantService.getIsolationMode()
        ));
    }

    @PostMapping("/switch")
    public ResponseEntity<Map<String, String>> switchTenant(@RequestParam String tenantId) {
        tenantService.switchTenant(tenantId);
        return ResponseEntity.ok(Map.of(
            "status", "SWITCHED",
            "activeTenant", tenantService.resolveCurrentTenant()
        ));
    }
}
