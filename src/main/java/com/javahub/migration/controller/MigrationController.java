package com.javahub.migration.controller;
import org.flywaydb.core.Flyway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/v1/migrations")
public class MigrationController {
    private final Flyway flyway;
    public MigrationController(Flyway flyway) { this.flyway = flyway; }
    @GetMapping("/history")
    public ResponseEntity<List<Map<String, Object>>> getHistory() {
        var info = flyway.info().all();
        List<Map<String, Object>> list = new ArrayList<>();
        for (var mi : info) {
            list.add(Map.of(
                "version", String.valueOf(mi.getVersion()),
                "description", mi.getDescription(),
                "type", mi.getType().name(),
                "state", mi.getState().name()
            ));
        }
        return ResponseEntity.ok(list);
    }
}
