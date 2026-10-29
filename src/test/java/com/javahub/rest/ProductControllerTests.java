package com.javahub.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/products returns seeded catalog")
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(2)));
    }

    @Test
    @DisplayName("POST /api/v1/products creates product with 201 and Location header")
    void testCreateSuccess() throws Exception {
        String payload = """
            {
                "name": "USB-C Hub",
                "sku": "ACC-USB-C",
                "price": 49.99
            }
        """;

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("USB-C Hub"));
    }

    @Test
    @DisplayName("POST /api/v1/products returns RFC 7807 ProblemDetail on validation failure")
    void testValidationFailure() throws Exception {
        String invalidPayload = """
            {
                "name": "",
                "sku": "",
                "price": -10.0
            }
        """;

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Invalid Request Content"))
                .andExpect(jsonPath("$.validationErrors.name").exists())
                .andExpect(jsonPath("$.validationErrors.price").exists());
    }

    @Test
    @DisplayName("GET /api/v1/products/{id} returns RFC 7807 404 for missing item")
    void testNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/products/non-existent-id"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Resource Not Found"));
    }
}
