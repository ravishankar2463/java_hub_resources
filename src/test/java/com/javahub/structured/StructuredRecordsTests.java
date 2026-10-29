package com.javahub.structured;
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
class StructuredRecordsTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify structured deserialization into typed Java 21 Record")
    void testStructuredOutput() throws Exception {
        mockMvc.perform(get("/api/v1/ai/review/analyze?file=PaymentService.java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filePath").value("PaymentService.java"))
                .andExpect(jsonPath("$.severity").value("MEDIUM"))
                .andExpect(jsonPath("$.recommendations").isArray());
    }
}
