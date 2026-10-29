package com.javahub.resilience;
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
class ResilienceApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Circuit transitions from CLOSED to OPEN after consecutive failures")
    void testCircuitBreakerTrips() throws Exception {
        mockMvc.perform(post("/api/v1/resilience/call?fail=true")).andExpect(status().isOk());
        mockMvc.perform(post("/api/v1/resilience/call?fail=true")).andExpect(status().isOk());
        mockMvc.perform(post("/api/v1/resilience/call?fail=true")).andExpect(status().isOk());
        mockMvc.perform(post("/api/v1/resilience/call?fail=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("OPEN"))
                .andExpect(jsonPath("$.result").value(org.hamcrest.Matchers.containsString("Fast-failed")));
    }
}
