package com.javahub.rag.controller;
import com.javahub.rag.service.RagPromptSynthesizerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/rag")
public class RagController {
    private final RagPromptSynthesizerService ragService;
    public RagController(RagPromptSynthesizerService ragService) { this.ragService = ragService; }
    @GetMapping("/prompt")
    public ResponseEntity<RagPromptSynthesizerService.RagPrompt> getRagPrompt(@RequestParam(defaultValue = "How do virtual threads work?") String query) {
        return ResponseEntity.ok(ragService.buildContext(query));
    }
}
