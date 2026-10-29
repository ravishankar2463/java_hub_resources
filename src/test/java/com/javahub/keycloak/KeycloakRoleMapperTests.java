package com.javahub.keycloak;

import com.javahub.keycloak.service.KeycloakRoleMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KeycloakRoleMapperTests {

    private final KeycloakRoleMapper roleMapper = new KeycloakRoleMapper();

    @Test
    @DisplayName("Extract realm_access roles into Spring Security ROLE_ authorities")
    void testRealmRoleMapping() {
        Jwt jwt = new Jwt(
            "dummy-token",
            Instant.now(),
            Instant.now().plusSeconds(300),
            Map.of("alg", "none"),
            Map.of(
                "sub", "user-uuid-101",
                "realm_access", Map.of("roles", List.of("offline_access", "uma_authorization", "engineer"))
            )
        );

        var authorities = roleMapper.convert(jwt);
        assertThat(authorities)
                .extracting("authority")
                .contains("ROLE_ENGINEER", "ROLE_OFFLINE_ACCESS");
    }
}
