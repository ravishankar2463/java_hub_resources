package com.javahub.ratelimit.controller;
import com.javahub.ratelimit.service.TokenBucketRateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/throttle")
public class RateLimiterController {
    private final TokenBucketRateLimiter rateLimiter;
    public RateLimiterController(TokenBucketRateLimiter rateLimiter) { this.rateLimiter = rateLimiter; }
    @GetMapping("/resource")
    public ResponseEntity<Map<String, Object>> accessResource() {
        if (!rateLimiter.tryAcquire()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of("error", "Rate limit exceeded (HTTP 429)", "retryAfterSeconds", 1));
        }
        return ResponseEntity.ok(Map.of("status", "SUCCESS", "remainingTokens", rateLimiter.availableTokens()));
    }
}
