package com.javahub.testing.slice;

import com.javahub.testing.controller.TaxController;
import com.javahub.testing.service.TaxCalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaxController.class)
class TaxControllerSliceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxCalculatorService taxService;

    @Test
    @DisplayName("Slice test (@WebMvcTest): verify endpoint binds query parameters and calls mock service")
    void testCalculateEndpoint() throws Exception {
        given(taxService.calculateVat(100.0, 0.20)).willReturn(20.00);

        mockMvc.perform(get("/api/v1/tax/calculate?amount=100.0&rate=0.20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subtotal").value(100.0))
                .andExpect(jsonPath("$.vat").value(20.0))
                .andExpect(jsonPath("$.total").value(120.0));
    }
}
