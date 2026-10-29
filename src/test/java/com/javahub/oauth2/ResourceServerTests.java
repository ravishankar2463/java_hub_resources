package com.javahub.oauth2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResourceServerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Public ping endpoint returns 200 without token")
    void testPublicPing() throws Exception {
        mockMvc.perform(get("/api/v1/public/ping"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RESOURCE_SERVER_HEALTHY"));
    }

    @Test
    @DisplayName("Protected orders endpoint rejects calls missing orders.read scope")
    void testOrdersWithoutScope() throws Exception {
        mockMvc.perform(get("/api/v1/orders/my-orders")
                .with(jwt().authorities(() -> "SCOPE_other.scope")))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Protected orders endpoint succeeds when token contains orders.read scope")
    void testOrdersWithScope() throws Exception {
        mockMvc.perform(get("/api/v1/orders/my-orders")
                .with(jwt().authorities(() -> "SCOPE_orders.read")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders").isArray());
    }
}
