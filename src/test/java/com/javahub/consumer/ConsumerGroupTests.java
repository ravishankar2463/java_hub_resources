package com.javahub.consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class ConsumerGroupTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Join members and verify cooperative partition rebalance across group")
    void testRebalance() throws Exception {
        mockMvc.perform(post("/api/v1/kafka/group/join?memberId=worker-1"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/v1/kafka/group/join?memberId=worker-2"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/kafka/group/assignments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
