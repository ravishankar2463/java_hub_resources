package com.javahub.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventsCachingTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify Spring @Cacheable caches database calls on duplicate requests")
    void testCaching() throws Exception {
        // First call: Cache miss (db hit count = 1)
        mockMvc.perform(get("/api/v1/demos/cache/price?sku=ITEM-42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dbHitCount").value(1));

        // Second call: Cache hit (db hit count remains 1)
        mockMvc.perform(get("/api/v1/demos/cache/price?sku=ITEM-42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dbHitCount").value(1));
    }

    @Test
    @DisplayName("Verify publishing event triggers registered synchronous event listener")
    void testEventPublishing() throws Exception {
        mockMvc.perform(post("/api/v1/demos/events/publish?orderId=ORD-99&email=test@test.com&amount=50.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EVENT_PUBLISHED"));

        mockMvc.perform(get("/api/v1/demos/events/log"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
    }
}
