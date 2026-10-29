package com.javahub.temporal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class TemporalApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Start durable workflow execution with deterministic event history tracking")
    void testWorkflowExecution() throws Exception {
        mockMvc.perform(post("/api/v1/temporal/workflows/start?id=LOAN-WF-101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.workflowId").value("LOAN-WF-101"))
                .andExpect(jsonPath("$.state").value("RUNNING_WAITING_FOR_TIMER"));
    }
}
