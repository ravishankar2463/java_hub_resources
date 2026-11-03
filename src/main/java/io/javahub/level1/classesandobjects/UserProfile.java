package io.javahub.level1.classesandobjects;

import java.util.Objects;

/**
 * Immutable user profile constructed via a validated fluent Builder.
 */
public final class UserProfile {

    private final String userId;
    private final String email;
    private final int age;
    private final boolean active;

    private UserProfile(Builder builder) {
        this.userId = builder.userId;
        this.email = builder.email;
        this.age = builder.age;
        this.active = builder.active;
    }

    public static class Builder {
        private final String userId;
        private String email;
        private int age;
        private boolean active = true;

        public Builder(String userId) {
            this.userId = Objects.requireNonNull(userId, "User ID cannot be null");
        }

        public Builder email(String email) {
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email format: " + email);
            }
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("Age out of legal bounds: " + age);
            }
            this.age = age;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public UserProfile build() {
            Objects.requireNonNull(email, "Email is mandatory before build()");
            return new UserProfile(this);
        }
    }

    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public boolean isActive() { return active; }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: Classes, Objects & Builder Pattern");
        System.out.println("================================================================================");
        UserProfile profile = new UserProfile.Builder("USR-9921")
                .email("alice.dev@javahub.io")
                .age(28)
                .active(true)
                .build();
        System.out.println("Constructed Immutable UserProfile:");
        System.out.println("  User ID : " + profile.getUserId());
        System.out.println("  Email   : " + profile.getEmail());
        System.out.println("  Age     : " + profile.getAge());
        System.out.println("  Active  : " + profile.isActive());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
