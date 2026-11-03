package io.javahub.level13.tuning;

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
class JvmTuningApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify JVM memory pools and GC collectors are exposed via diagnostic API")
    void shouldExposeMemoryPools() throws Exception {
        mockMvc.perform(get("/api/v1/jvm/memory-pools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memoryPools").isArray())
                .andExpect(jsonPath("$.garbageCollectors").isArray());
    }
}
