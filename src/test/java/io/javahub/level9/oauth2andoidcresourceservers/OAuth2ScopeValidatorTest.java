package io.javahub.level9.oauth2andoidcresourceservers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class OAuth2ScopeValidatorTest {

    @Test
    @DisplayName("Should authorize access when token holds required scope or wildcard")
    void testScopeValidation() {
        var validator = new OAuth2ScopeValidator();

        assertThat(validator.authorizeScope(Set.of("orders:read"), "orders:read")).isTrue();
        assertThat(validator.authorizeScope(Set.of("orders:read"), "orders:write")).isFalse();
        assertThat(validator.authorizeScope(Set.of("admin:*"), "orders:write")).isTrue();
    }
}
