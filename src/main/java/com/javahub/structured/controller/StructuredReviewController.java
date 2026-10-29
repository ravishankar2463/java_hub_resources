package com.javahub.structured.controller;
import com.javahub.structured.dto.CodeReviewAnalysis;
import com.javahub.structured.service.StructuredLlmParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/ai/review")
public class StructuredReviewController {
    private final StructuredLlmParserService parserService;
    public StructuredReviewController(StructuredLlmParserService parserService) { this.parserService = parserService; }
    @GetMapping("/analyze")
    public ResponseEntity<CodeReviewAnalysis> analyze(@RequestParam(defaultValue = "OrderService.java") String file) {
        return ResponseEntity.ok(parserService.parseCodeReview(file));
    }
}
