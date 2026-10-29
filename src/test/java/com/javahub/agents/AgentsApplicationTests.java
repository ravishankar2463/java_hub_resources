package com.javahub.agents;
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
class AgentsApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify autonomous multi-agent supervisor loop execution")
    void testAgentMission() throws Exception {
        mockMvc.perform(post("/api/v1/agents/missions?id=MISSION-1&task=DesignMemoryPool"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("COMPLETED"))
                .andExpect(jsonPath("$.agentTrace.length()").value(3));
    }
}
