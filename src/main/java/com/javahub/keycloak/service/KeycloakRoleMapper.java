package com.javahub.keycloak.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class KeycloakRoleMapper implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // 1. Extract Realm Access Roles
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
        }

        // 2. Extract Resource/Client Roles
        Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");
        if (resourceAccess != null) {
            resourceAccess.values().forEach(client -> {
                if (client instanceof Map<?, ?> clientMap && clientMap.containsKey("roles")) {
                    List<String> clientRoles = (List<String>) clientMap.get("roles");
                    clientRoles.forEach(r -> authorities.add(new SimpleGrantedAuthority("CLIENT_ROLE_" + r.toUpperCase())));
                }
            });
        }

        return authorities;
    }
}
