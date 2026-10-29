package com.javahub.dlq;
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
class DlqApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Poison pill event is redirected to DLQ after exhaustion of retries")
    void testDlqRouting() throws Exception {
        mockMvc.perform(post("/api/v1/kafka/dlq/simulate-poison-pill?payload=MALFORMED_DATA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ROUTED_TO_DLQ"));
        mockMvc.perform(get("/api/v1/kafka/dlq/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].failureReason").exists());
    }
}
