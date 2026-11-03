package io.javahub.level12.serverless;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ServerlessApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify Spring Cloud Function serverless execution via HTTP POST")
    void shouldExecuteServerlessOrderFunction() throws Exception {
        String jsonPayload = """
                {
                    "customerId": "CUST-9901",
                    "amount": 250.75
                }
                """;

        mockMvc.perform(post("/processOrderFunction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").isNotEmpty())
                .andExpect(jsonPath("$.status").value("APPROVED"))
                .andExpect(jsonPath("$.processedAmount").value(250.75));
    }
}
