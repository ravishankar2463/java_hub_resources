package com.javahub.contract.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/provider")
public class ProviderContractController {
    @GetMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> getUserContract(@PathVariable String id) {
        return ResponseEntity.ok(Map.of(
            "id", id,
            "name", "Jane Doe",
            "email", "jane@company.org",
            "status", "ACTIVE"
        ));
    }
}
