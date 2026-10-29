package com.javahub.ai.controller;
import com.javahub.ai.service.AiChatClientSimulator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/ai/chat")
public class AiChatController {
    private final AiChatClientSimulator chatClient;
    public AiChatController(AiChatClientSimulator chatClient) { this.chatClient = chatClient; }
    @PostMapping("/generate")
    public ResponseEntity<AiChatClientSimulator.ChatResponse> generate(
            @RequestParam String prompt, @RequestParam(defaultValue = "You are a senior Java architect.") String system) {
        return ResponseEntity.ok(chatClient.complete(prompt, system));
    }
}
