package com.javahub.level14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Enterprise AI: Spring AI ChatClient Simulator.
 * Demonstrates prompt engineering, role-based messaging (SYSTEM, USER, ASSISTANT),
 * token counting heuristics, and temperature-controlled model responses.
 */
public class SpringAiChatClientSimulator {

    public enum Role {
        SYSTEM, USER, ASSISTANT
    }

    public record ChatMessage(Role role, String content) {
        public ChatMessage {
            Objects.requireNonNull(role, "Role must not be null");
            Objects.requireNonNull(content, "Content must not be null");
        }
    }

    public record PromptOptions(double temperature, int maxTokens, String modelName) {}

    public record GenerationResponse(String output, int promptTokens, int completionTokens, String finishReason) {}

    private final String defaultModel;
    private final List<ChatMessage> conversationHistory = new ArrayList<>();

    public SpringAiChatClientSimulator(String defaultModel) {
        this.defaultModel = defaultModel != null ? defaultModel : "gpt-4o";
    }

    public synchronized void addSystemMessage(String instruction) {
        conversationHistory.add(new ChatMessage(Role.SYSTEM, instruction));
    }

    public synchronized GenerationResponse call(String userPrompt, PromptOptions options) {
        conversationHistory.add(new ChatMessage(Role.USER, userPrompt));

        int promptTokens = estimateTokens(userPrompt);
        String generatedText = "Simulated response for: " + userPrompt + " [Model: " + (options != null ? options.modelName() : defaultModel) + "]";
        int completionTokens = estimateTokens(generatedText);

        conversationHistory.add(new ChatMessage(Role.ASSISTANT, generatedText));

        return new GenerationResponse(generatedText, promptTokens, completionTokens, "stop");
    }

    public synchronized List<ChatMessage> getConversationHistory() {
        return Collections.unmodifiableList(new ArrayList<>(conversationHistory));
    }

    private int estimateTokens(String text) {
        if (text == null || text.isBlank()) return 0;
        return (int) Math.ceil(text.split("\\s+").length * 1.3);
    }
}
