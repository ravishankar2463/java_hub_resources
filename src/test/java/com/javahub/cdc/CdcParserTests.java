package com.javahub.cdc;
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
class CdcParserTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify parsing Debezium envelope for UPDATE operation")
    void testCdcParsing() throws Exception {
        String payload = "{\"op\":\"u\",\"before\":{\"id\":101,\"tier\":\"SILVER\"},\"after\":{\"id\":101,\"tier\":\"GOLD\"},\"timestamp\":1690000000}";
        mockMvc.perform(post("/api/v1/cdc/parse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary").exists());
    }
}
