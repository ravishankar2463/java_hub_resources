package com.javahub.ai;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class SpringAiApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify Spring AI chat client generation and token accounting")
    void testChatGeneration() throws Exception {
        mockMvc.perform(post("/api/v1/ai/chat/generate?prompt=ExplainVirtualThreads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("gpt-4o"))
                .andExpect(jsonPath("$.promptTokens").isNumber());
    }
}
