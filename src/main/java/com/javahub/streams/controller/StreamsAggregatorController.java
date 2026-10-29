package com.javahub.streams.controller;
import com.javahub.streams.service.TumblingWindowAggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/streams")
public class StreamsAggregatorController {
    private final TumblingWindowAggregationService aggregationService;
    public StreamsAggregatorController(TumblingWindowAggregationService aggregationService) { this.aggregationService = aggregationService; }
    @PostMapping("/trades")
    public ResponseEntity<TumblingWindowAggregationService.WindowSummary> postTrade(@RequestParam String ticker, @RequestParam double amount) {
        return ResponseEntity.ok(aggregationService.recordTrade(ticker, amount));
    }
}
