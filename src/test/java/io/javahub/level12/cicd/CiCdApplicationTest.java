package io.javahub.level12.cicd;

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
class CiCdApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify CI/CD status endpoint responds with valid pipeline telemetry")
    void shouldReturnPipelineStatus() throws Exception {
        mockMvc.perform(get("/api/v1/cicd/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.qualityGate").value("PASSED"));
    }
}
