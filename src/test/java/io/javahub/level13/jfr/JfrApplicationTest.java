package io.javahub.level13.jfr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JfrApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify custom JFR payment event commits to runtime event pipeline")
    void shouldCommitJfrEvent() throws Exception {
        mockMvc.perform(post("/api/v1/profiling/simulate-workload").param("amount", "250.50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").isNotEmpty())
                .andExpect(jsonPath("$.jfrEventCommitted").value(true));
    }
}
