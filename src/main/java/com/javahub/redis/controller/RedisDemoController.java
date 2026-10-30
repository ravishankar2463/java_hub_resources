package com.javahub.redis.controller;
import com.javahub.redis.service.CacheAsideSimulatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/redis")
public class RedisDemoController {
    private final CacheAsideSimulatorService cacheService;
    public RedisDemoController(CacheAsideSimulatorService cacheService) { this.cacheService = cacheService; }
    @GetMapping("/get")
    public ResponseEntity<Map<String, String>> get(@RequestParam String key) {
        return ResponseEntity.ok(Map.of("result", cacheService.getWithCacheAside(key)));
    }
}
