package com.javahub.nosql;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class NoSqlTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify schema-less document storage and retrieval")
    void testDocumentStore() throws Exception {
        mockMvc.perform(post("/api/v1/nosql/docs/user-profile-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"theme\":\"dark\",\"tags\":[\"java\",\"spring\"]}"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/nosql/docs/user-profile-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theme").value("dark"));
    }
}
