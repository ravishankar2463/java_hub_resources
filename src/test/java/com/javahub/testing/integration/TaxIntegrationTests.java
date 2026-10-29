package com.javahub.testing.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaxIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Integration test (@SpringBootTest): boots full container and verifies end-to-end response")
    void testEndToEndCalculation() throws Exception {
        mockMvc.perform(get("/api/v1/tax/calculate?amount=250.0&rate=0.10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vat").value(25.0))
                .andExpect(jsonPath("$.total").value(275.0));
    }
}
