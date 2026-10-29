package io.javahub.level9.springsecurityarchitecture;

import java.util.List;

/**
 * Emulates the Spring Security FilterChain and Principal authentication.
 */
public class SecurityFilterChainSimulator {

    public record SecurityContext(String username, List<String> roles) {}

    private static final ThreadLocal<SecurityContext> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setContext(SecurityContext context) {
        CONTEXT_HOLDER.set(context);
    }

    public static SecurityContext getContext() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearContext() {
        CONTEXT_HOLDER.remove();
    }

    public boolean hasRole(String role) {
        SecurityContext ctx = getContext();
        return ctx != null && ctx.roles().contains(role);
    }
}
