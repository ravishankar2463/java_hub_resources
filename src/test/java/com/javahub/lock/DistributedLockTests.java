package com.javahub.lock;
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
class DistributedLockTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Mutual exclusion: second lock acquisition fails while resource is held")
    void testMutualExclusion() throws Exception {
        mockMvc.perform(post("/api/v1/lock/acquire?resource=INVENTORY-10&owner=node-A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.acquired").value(true));
        mockMvc.perform(post("/api/v1/lock/acquire?resource=INVENTORY-10&owner=node-B"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.acquired").value(false));
    }
}
