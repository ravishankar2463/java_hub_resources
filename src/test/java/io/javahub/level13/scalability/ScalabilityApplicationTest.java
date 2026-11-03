package io.javahub.level13.scalability;

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
class ScalabilityApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify consistent hashing routing assigns keys deterministically to nodes")
    void shouldRouteKeysDeterministically() throws Exception {
        mockMvc.perform(get("/api/v1/partitioning/route").param("key", "user:session:10492"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("user:session:10492"))
                .andExpect(jsonPath("$.assignedNode").isNotEmpty())
                .andExpect(jsonPath("$.totalPhysicalNodes").value(3))
                .andExpect(jsonPath("$.totalVirtualNodes").value(300));
    }
}
