package com.javahub.migration;
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
class MigrationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify Flyway migrations V1 and V2 executed successfully on startup")
    void testFlywayHistory() throws Exception {
        mockMvc.perform(get("/api/v1/migrations/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].version").value("1"))
                .andExpect(jsonPath("$[1].version").value("2"));
    }
}
