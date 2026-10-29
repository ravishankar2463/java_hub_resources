package com.javahub.jwt;

import com.javahub.jwt.service.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JwtApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("Login with valid credentials yields JWT Bearer token")
    void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login?username=admin&password=secret"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.tokenType").value("Bearer"));
    }

    @Test
    @DisplayName("Protected endpoint succeeds when valid JWT Bearer header is presented")
    void testAccessWithJwt() throws Exception {
        String token = jwtService.generateToken("alice_engineer", "ADMIN");

        mockMvc.perform(get("/api/v1/secure/data")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("alice_engineer"));
    }

    @Test
    @DisplayName("Protected endpoint fails with 403 Forbidden when token is missing")
    void testAccessWithoutJwt() throws Exception {
        mockMvc.perform(get("/api/v1/secure/data"))
                .andExpect(status().isForbidden());
    }
}
