package com.javahub.sql.controller;
import com.javahub.sql.service.WindowFunctionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/sql")
public class WindowFunctionController {
    private final WindowFunctionService service;
    public WindowFunctionController(WindowFunctionService service) { this.service = service; }
    @GetMapping("/window-ranking")
    public ResponseEntity<List<Map<String, Object>>> getRankings() {
        return ResponseEntity.ok(service.getRankedSalariesByDepartment());
    }
}
