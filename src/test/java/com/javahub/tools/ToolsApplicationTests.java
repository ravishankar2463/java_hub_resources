package com.javahub.tools;
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
class ToolsApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify dynamic LLM tool dispatch executes Java function")
    void testToolDispatch() throws Exception {
        mockMvc.perform(post("/api/v1/tools/dispatch?tool=checkFlightStatus&argument=BA-117"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.output").value("Flight BA-117 is ON_TIME"));
    }
}
