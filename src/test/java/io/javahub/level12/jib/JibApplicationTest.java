package io.javahub.level12.jib;

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
class JibApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Verify Jib layering metadata API responds correctly")
    void shouldReturnLayeringInfo() throws Exception {
        mockMvc.perform(get("/api/v1/jib/layers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.daemonRequired").value(false))
                .andExpect(jsonPath("$.buildEngine").value("Google Jib Maven Plugin"));
    }
}
