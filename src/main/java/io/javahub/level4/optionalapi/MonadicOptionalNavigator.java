package io.javahub.level4.optionalapi;

import java.util.Optional;

/**
 * Safe navigation and value transformations eliminating NullPointerException hazards.
 */
public class MonadicOptionalNavigator {

    public record Address(String city) {}
    public record User(String name, Address address) {}

    public String resolveCityOrDefault(Optional<User> userOpt, String fallback) {
        return userOpt
            .map(User::address)
            .map(Address::city)
            .filter(city -> !city.isBlank())
            .orElse(fallback);
    }
}
