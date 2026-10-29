package com.javahub.interservice;
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
class InterServiceTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify inter-service client invocation metadata")
    void testRemoteCall() throws Exception {
        mockMvc.perform(get("/api/v1/communication/call?service=InventoryService"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.target").value("InventoryService"))
                .andExpect(jsonPath("$.status").value("CONNECTED"));
    }
}
