package io.javahub.level9.oauth2andoidcresourceservers;

import java.util.Set;

/**
 * Validates OAuth2 token scopes against endpoint permission requirements.
 */
public class OAuth2ScopeValidator {

    public boolean authorizeScope(Set<String> tokenScopes, String requiredScope) {
        return tokenScopes.contains(requiredScope) || tokenScopes.contains("admin:*");
    }
}
