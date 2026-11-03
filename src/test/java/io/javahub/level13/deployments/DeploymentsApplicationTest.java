package io.javahub.level13.deployments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeploymentsApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify canary weight adjustments and traffic routing decisions")
    void shouldAdjustCanaryWeightAndRoute() throws Exception {
        mockMvc.perform(post("/api/v1/deployments/canary-weight").param("weight", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.newWeight").value(50));

        mockMvc.perform(get("/api/v1/deployments/route"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetDeployment").isNotEmpty());
    }
}
