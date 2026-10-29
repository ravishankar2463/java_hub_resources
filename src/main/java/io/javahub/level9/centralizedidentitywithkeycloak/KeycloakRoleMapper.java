package io.javahub.level9.centralizedidentitywithkeycloak;

import java.util.ArrayList;
import java.util.List;

/**
 * Translates Keycloak JSON token realm and client roles into Spring GrantedAuthorities.
 */
public class KeycloakRoleMapper {

    public List<String> mapAuthorities(List<String> realmRoles, List<String> clientRoles) {
        List<String> authorities = new ArrayList<>();
        for (String r : realmRoles) {
            authorities.add("ROLE_REALM_" + r.toUpperCase());
        }
        for (String c : clientRoles) {
            authorities.add("ROLE_CLIENT_" + c.toUpperCase());
        }
        return authorities;
    }
}
