package com.javahub.streaming;

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
class StreamingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify append-only log production and offset-based playback")
    void testProduceAndConsume() throws Exception {
        mockMvc.perform(post("/api/v1/stream/produce?key=ORD-01&payload=PaymentCaptured"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.offset").value(0))
                .andExpect(jsonPath("$.key").value("ORD-01"));

        mockMvc.perform(get("/api/v1/stream/consume?partition=1&offset=0"))
                .andExpect(status().isOk());
    }
}
