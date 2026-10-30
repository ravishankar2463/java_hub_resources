package com.javahub.engine;
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
class BTreeEngineTests {
    @Autowired private MockMvc mockMvc;
    @Test
    @DisplayName("Verify B-Tree index point seek locates physical page pointer")
    void testBTreeSeek() throws Exception {
        mockMvc.perform(get("/api/v1/engine/btree/seek?key=200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pagePointer").value("Page#12 [Offset: 0x01E0]"))
                .andExpect(jsonPath("$.lookupComplexity").value("O(log B N)"));
    }
}
