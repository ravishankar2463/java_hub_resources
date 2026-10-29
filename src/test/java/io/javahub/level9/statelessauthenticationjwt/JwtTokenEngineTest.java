package io.javahub.level9.statelessauthenticationjwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JwtTokenEngineTest {

    private final JwtTokenEngine engine = new JwtTokenEngine();

    @Test
    @DisplayName("Should format three-part compact JWT (Header.Payload.Signature)")
    void testTokenStructure() {
        String token = engine.generateCompactToken("user_42", 1700000000L);
        String[] parts = token.split("\\.");

        assertThat(parts).hasSize(3);
    }

    @Test
    @DisplayName("Should detect expired token based on expiration epoch")
    void testExpiration() {
        assertThat(engine.isExpired(1000L, 2000L)).isTrue();
        assertThat(engine.isExpired(3000L, 2000L)).isFalse();
    }
}
