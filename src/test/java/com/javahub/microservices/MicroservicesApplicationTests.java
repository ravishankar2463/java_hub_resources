package com.javahub.microservices;
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
class MicroservicesApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify Bounded Context metadata endpoint")
    void testContextInfo() throws Exception {
        mockMvc.perform(get("/api/v1/context/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.databaseOwnership").value("PRIVATE_ORDER_DB"));
    }
}
