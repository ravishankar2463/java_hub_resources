package com.javahub.streams;
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
class StreamsAggregatorTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify stream window aggregation accumulates trade volume")
    void testWindowAggregation() throws Exception {
        mockMvc.perform(post("/api/v1/streams/trades?ticker=AAPL&amount=150.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalSum").value(150.0));
        mockMvc.perform(post("/api/v1/streams/trades?ticker=AAPL&amount=250.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalSum").value(400.0))
                .andExpect(jsonPath("$.count").value(2));
    }
}
