package com.javahub.producer;
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
class ProducerApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Duplicate producer message with same PID and sequence is rejected idempotently")
    void testIdempotentPublish() throws Exception {
        mockMvc.perform(post("/api/v1/orders/publish?orderId=ORD-99&payload=Checkout&sequence=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duplicateFiltered").value(false));
        mockMvc.perform(post("/api/v1/orders/publish?orderId=ORD-99&payload=Checkout&sequence=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duplicateFiltered").value(true));
    }
}
