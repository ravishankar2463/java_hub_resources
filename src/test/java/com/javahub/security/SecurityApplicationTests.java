package com.javahub.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Public endpoint is accessible anonymously")
    void testPublicEndpoint() throws Exception {
        mockMvc.perform(get("/api/v1/public/welcome"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access").value("ANONYMOUS"));
    }

    @Test
    @DisplayName("Protected endpoint returns 401 Unauthorized when unauthenticated")
    void testProtectedWithoutAuth() throws Exception {
        mockMvc.perform(get("/api/v1/user/profile"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "jane", roles = {"USER"})
    @DisplayName("Protected endpoint returns 200 with authenticated USER")
    void testProtectedWithUser() throws Exception {
        mockMvc.perform(get("/api/v1/user/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("jane"))
                .andExpect(jsonPath("$.status").value("AUTHENTICATED"));
    }

    @Test
    @WithMockUser(username = "bob", roles = {"USER"})
    @DisplayName("Regular USER receives 403 Forbidden on ADMIN endpoint")
    void testAdminForbiddenForRegularUser() throws Exception {
        mockMvc.perform(get("/api/v1/admin/system-status"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "alice", roles = {"ADMIN"})
    @DisplayName("ADMIN role receives 200 on ADMIN endpoint")
    void testAdminSuccess() throws Exception {
        mockMvc.perform(get("/api/v1/admin/system-status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operator").value("alice"));
    }
}
