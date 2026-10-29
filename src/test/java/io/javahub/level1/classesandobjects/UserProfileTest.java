package io.javahub.level1.classesandobjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserProfileTest {

    @Test
    @DisplayName("Should build immutable UserProfile with valid fields")
    void testBuilderSuccess() {
        UserProfile profile = new UserProfile.Builder("USR-101")
            .email("alice@javahub.io")
            .age(30)
            .active(true)
            .build();

        assertThat(profile.getUserId()).isEqualTo("USR-101");
        assertThat(profile.getEmail()).isEqualTo("alice@javahub.io");
        assertThat(profile.getAge()).isEqualTo(30);
        assertThat(profile.isActive()).isTrue();
    }

    @Test
    @DisplayName("Should fail when email format is invalid")
    void testInvalidEmail() {
        assertThatThrownBy(() -> new UserProfile.Builder("USR-102").email("not-an-email"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid email format");
    }
}
