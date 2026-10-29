package com.javahub.ai.service;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class AiChatClientSimulator {
    public record ChatResponse(String model, String userQuery, String completion, int promptTokens, int completionTokens) {}
    public ChatResponse complete(String userQuery, String systemInstruction) {
        int pTokens = (int) Math.ceil(userQuery.split("\\s+").length * 1.3);
        String response = "AI Insight for ['" + userQuery + "']: Configured with system rule: " + (systemInstruction != null ? systemInstruction : "Default Assistant");
        int cTokens = (int) Math.ceil(response.split("\\s+").length * 1.3);
        return new ChatResponse("gpt-4o", userQuery, response, pTokens, cTokens);
    }
}
