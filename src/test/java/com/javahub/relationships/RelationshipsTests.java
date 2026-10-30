package com.javahub.relationships;
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
class RelationshipsTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify cascading persist for bidirectional Author-Book association")
    void testCascade() throws Exception {
        mockMvc.perform(post("/api/v1/relationships/author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookCount").value(2));
    }
}
