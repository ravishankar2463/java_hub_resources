package com.javahub.starter;

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
class MultiTenantAutoConfigurationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify auto-configured TenantContextService exposes configured properties")
    void testTenantAutoConfiguration() throws Exception {
        mockMvc.perform(get("/api/v1/tenant/current"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tenantId").value("production-eu"))
                .andExpect(jsonPath("$.isolationMode").value("DATABASE_PER_TENANT"));
    }
}
