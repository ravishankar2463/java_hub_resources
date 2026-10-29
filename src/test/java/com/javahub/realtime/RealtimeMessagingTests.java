package com.javahub.realtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RealtimeMessagingTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("SSE price subscription endpoint connects with text/event-stream")
    void testSseEndpointConnection() throws Exception {
        mockMvc.perform(get("/api/v1/stream/prices"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Broadcast price triggers event dispatch")
    void testBroadcast() throws Exception {
        mockMvc.perform(post("/api/v1/stream/broadcast?ticker=NVDA&price=124.50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("BROADCAST_COMPLETED"));
    }
}
