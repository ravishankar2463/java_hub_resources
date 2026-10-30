package com.javahub.basics.controller;
import com.javahub.basics.service.PersistenceContextDemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/jpa")
public class JpaBasicsController {
    private final PersistenceContextDemoService demoService;
    public JpaBasicsController(PersistenceContextDemoService demoService) { this.demoService = demoService; }
    @PostMapping("/dirty-checking")
    public ResponseEntity<Map<String, Object>> testDirtyCheck() {
        return ResponseEntity.ok(demoService.demonstrateDirtyChecking());
    }
}
