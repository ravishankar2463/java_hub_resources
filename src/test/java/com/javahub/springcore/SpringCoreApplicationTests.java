package com.javahub.springcore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringCoreApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/container/info returns bean definitions count")
    void testContainerInfo() throws Exception {
        mockMvc.perform(get("/api/v1/container/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RUNNING"))
                .andExpect(jsonPath("$.beanDefinitionCount").isNumber());
    }

    @Test
    @DisplayName("POST /api/v1/container/orders/process processes order through Spring bean")
    void testProcessOrder() throws Exception {
        mockMvc.perform(post("/api/v1/container/orders/process?orderId=ORD-9901"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("ORD-9901"))
                .andExpect(jsonPath("$.result").value("ORDER_PROCESSED: ORD-9901"));
    }

    @Test
    @DisplayName("GET /api/v1/container/reports/prototype-demo produces distinct prototype instances")
    void testPrototypeResolution() throws Exception {
        mockMvc.perform(get("/api/v1/container/reports/prototype-demo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", containsString("Financial Q3")))
                .andExpect(jsonPath("$[1]", containsString("Operations Q3")));
    }
}
