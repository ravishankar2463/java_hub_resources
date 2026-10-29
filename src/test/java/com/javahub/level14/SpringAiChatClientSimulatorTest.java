package com.javahub.level14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class SpringAiChatClientSimulatorTest {

    @Test
    @DisplayName("Verify Spring AI ChatClient role management and generation")
    void testChatClientFlow() {
        SpringAiChatClientSimulator client = new SpringAiChatClientSimulator("claude-3-5-sonnet");
        client.addSystemMessage("You are an expert Java architect.");

        SpringAiChatClientSimulator.PromptOptions options = new SpringAiChatClientSimulator.PromptOptions(0.7, 500, "claude-3-5-sonnet");
        SpringAiChatClientSimulator.GenerationResponse response = client.call("Explain Virtual Threads", options);

        assertThat(response.output()).contains("Virtual Threads");
        assertThat(response.promptTokens()).isGreaterThan(0);
        assertThat(response.finishReason()).isEqualTo("stop");

        var history = client.getConversationHistory();
        assertThat(history).hasSize(3);
        assertThat(history.get(0).role()).isEqualTo(SpringAiChatClientSimulator.Role.SYSTEM);
        assertThat(history.get(1).role()).isEqualTo(SpringAiChatClientSimulator.Role.USER);
        assertThat(history.get(2).role()).isEqualTo(SpringAiChatClientSimulator.Role.ASSISTANT);
    }
}
