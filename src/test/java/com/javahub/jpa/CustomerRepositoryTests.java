package com.javahub.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerRepositoryTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/customers/active returns active customers")
    void testGetActiveCustomers() throws Exception {
        mockMvc.perform(get("/api/v1/customers/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Alice Johnson"));
    }

    @Test
    @DisplayName("GET /api/v1/customers/{id}/orders solves N+1 problem using @EntityGraph")
    void testGetCustomerOrders() throws Exception {
        mockMvc.perform(get("/api/v1/customers/1/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders", hasSize(2)))
                .andExpect(jsonPath("$.orders[0].orderNumber").value("ORD-101"));
    }
}
