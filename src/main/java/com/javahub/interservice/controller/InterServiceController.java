package com.javahub.interservice.controller;
import com.javahub.interservice.service.DownstreamCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/communication")
public class InterServiceController {
    private final DownstreamCallService callService;
    public InterServiceController(DownstreamCallService callService) { this.callService = callService; }
    @GetMapping("/call")
    public ResponseEntity<Map<String, Object>> call(@RequestParam(defaultValue = "PaymentGateway") String service) {
        return ResponseEntity.ok(callService.executeRemoteCall(service));
    }
}
