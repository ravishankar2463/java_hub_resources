package io.javahub.level4.optionalapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class MonadicOptionalNavigatorTest {

    private final MonadicOptionalNavigator navigator = new MonadicOptionalNavigator();

    @Test
    @DisplayName("Should navigate nested address fields safely through Optional map")
    void testOptionalPresent() {
        var user = new MonadicOptionalNavigator.User("Alice", new MonadicOptionalNavigator.Address("Zurich"));
        String city = navigator.resolveCityOrDefault(Optional.of(user), "Unknown");

        assertThat(city).isEqualTo("Zurich");
    }

    @Test
    @DisplayName("Should return fallback when address or user is missing")
    void testOptionalFallback() {
        String city = navigator.resolveCityOrDefault(Optional.empty(), "Unknown");
        assertThat(city).isEqualTo("Unknown");
    }
}
