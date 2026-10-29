package com.javahub.microservices.controller;
import com.javahub.microservices.service.BoundedContextBoundaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/context")
public class BoundaryController {
    private final BoundedContextBoundaryService boundaryService;
    public BoundaryController(BoundedContextBoundaryService boundaryService) { this.boundaryService = boundaryService; }
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfo() { return ResponseEntity.ok(boundaryService.describeContext()); }
}
