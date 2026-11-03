package io.javahub.level12.docker;

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
class DockerApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify container diagnostics returns valid JVM memory and processor alignment")
    void shouldReturnContainerDiagnostics() throws Exception {
        mockMvc.perform(get("/api/v1/container/diagnostics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jvmAvailableProcessors").isNumber())
                .andExpect(jsonPath("$.jvmMaxMemoryMb").isNumber())
                .andExpect(jsonPath("$.status").value("ACTIVE_AND_CONSTRAINED"));
    }
}
