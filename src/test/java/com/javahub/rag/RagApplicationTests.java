package com.javahub.rag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class RagApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify contextual prompt synthesis injects knowledge chunks")
    void testRagPrompt() throws Exception {
        mockMvc.perform(get("/api/v1/rag/prompt?query=Loom"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.systemContext").exists())
                .andExpect(jsonPath("$.chunksIncluded").value(1));
    }
}
