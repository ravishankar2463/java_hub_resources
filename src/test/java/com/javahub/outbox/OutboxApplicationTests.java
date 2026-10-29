package com.javahub.outbox;

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
class OutboxApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify atomic order outbox creation and scheduled relay")
    void testOutboxCycle() throws Exception {
        mockMvc.perform(post("/api/v1/outbox/orders?orderId=ORD-909&amount=550.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("ORD-909"))
                .andExpect(jsonPath("$.status").value("PENDING"));

        // Wait brief moment for scheduled relay
        Thread.sleep(2500);

        mockMvc.perform(get("/api/v1/outbox/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("PUBLISHED"));
    }
}
