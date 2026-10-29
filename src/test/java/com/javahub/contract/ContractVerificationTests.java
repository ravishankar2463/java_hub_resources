package com.javahub.contract;
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
class ContractVerificationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Provider satisfies consumer contract JSON schema expectations")
    void testProviderContract() throws Exception {
        mockMvc.perform(get("/api/v1/provider/users/USER-88"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("USER-88"))
                .andExpect(jsonPath("$.email").isString());
    }
}
