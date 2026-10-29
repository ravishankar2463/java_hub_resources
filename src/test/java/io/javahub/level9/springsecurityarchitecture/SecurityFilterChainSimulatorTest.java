package io.javahub.level9.springsecurityarchitecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class SecurityFilterChainSimulatorTest {

    @Test
    @DisplayName("Should propagate SecurityContext via ThreadLocal and verify authorized roles")
    void testSecurityContextHolder() {
        var simulator = new SecurityFilterChainSimulator();

        SecurityFilterChainSimulator.setContext(
            new SecurityFilterChainSimulator.SecurityContext("alice", List.of("ROLE_ADMIN", "ROLE_USER"))
        );

        try {
            assertThat(simulator.hasRole("ROLE_ADMIN")).isTrue();
            assertThat(simulator.hasRole("ROLE_SUPERVISOR")).isFalse();
        } finally {
            SecurityFilterChainSimulator.clearContext();
        }

        assertThat(SecurityFilterChainSimulator.getContext()).isNull();
    }
}
