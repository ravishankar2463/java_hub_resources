package com.javahub.saga;
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
class SagaApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Saga rolls back and executes compensating transactions on failure")
    void testSagaCompensation() throws Exception {
        mockMvc.perform(post("/api/v1/saga/orders/execute?orderId=SAGA-101&failPayment=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("COMPENSATED_FAILED"))
                .andExpect(jsonPath("$.compensationsTriggered.length()").value(1));
    }
}
