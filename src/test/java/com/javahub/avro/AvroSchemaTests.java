package com.javahub.avro;
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
class AvroSchemaTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Register valid Avro schema into registry")
    void testRegisterSchema() throws Exception {
        String avroJson = "{\"schema\": \"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"UserEvent\\\",\\\"fields\\\":[{\\\"name\\\":\\\"id\\\",\\\"type\\\":\\\"string\\\"}]}\"}";
        mockMvc.perform(post("/api/v1/schemas/subjects/user-events/versions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(avroJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value(1));
    }
}
