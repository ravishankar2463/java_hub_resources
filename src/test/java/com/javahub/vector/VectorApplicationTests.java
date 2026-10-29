package com.javahub.vector;
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
class VectorApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify semantic vector search ranks nearest document first")
    void testVectorSearch() throws Exception {
        mockMvc.perform(get("/api/v1/vectors/search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("doc-1"));
    }
}
