package io.javahub.level9.centralizedidentitywithkeycloak;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class KeycloakRoleMapperTest {

    @Test
    @DisplayName("Should prefix realm and client roles into normalized Spring GrantedAuthorities")
    void testRoleMapping() {
        var mapper = new KeycloakRoleMapper();
        var authorities = mapper.mapAuthorities(List.of("admin"), List.of("order-manager"));

        assertThat(authorities).containsExactlyInAnyOrder("ROLE_REALM_ADMIN", "ROLE_CLIENT_ORDER-MANAGER");
    }
}
