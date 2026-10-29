package com.javahub.avro.controller;
import com.javahub.avro.service.AvroSchemaValidatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/schemas")
public class SchemaRegistryController {
    private final AvroSchemaValidatorService validatorService;
    public SchemaRegistryController(AvroSchemaValidatorService validatorService) { this.validatorService = validatorService; }
    @PostMapping("/subjects/{subject}/versions")
    public ResponseEntity<Map<String, Object>> register(@PathVariable String subject, @RequestBody Map<String, String> body) {
        String json = body.get("schema");
        boolean valid = validatorService.validateAndRegister(subject, json);
        if (!valid) return ResponseEntity.badRequest().body(Map.of("error", "Invalid Avro Schema definition"));
        return ResponseEntity.ok(Map.of("subject", subject, "version", validatorService.getVersion(subject), "status", "REGISTERED"));
    }
}
