package io.javahub.level12.k8s;

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
class K8sApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify Kubernetes liveness and readiness probe transition dynamics")
    void shouldToggleReadinessState() throws Exception {
        // Initial state
        mockMvc.perform(get("/api/v1/k8s/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.readiness").value("ACCEPTING_TRAFFIC"));

        // Drain traffic
        mockMvc.perform(post("/api/v1/k8s/simulate-traffic-drain"))
                .andExpect(status().isOk());

        // Verify status degraded
        mockMvc.perform(get("/api/v1/k8s/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.readiness").value("REFUSING_TRAFFIC"));

        // Restore traffic
        mockMvc.perform(post("/api/v1/k8s/simulate-traffic-accept"))
                .andExpect(status().isOk());
    }
}
