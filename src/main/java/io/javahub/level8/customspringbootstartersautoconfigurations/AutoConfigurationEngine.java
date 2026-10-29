package io.javahub.level8.customspringbootstartersautoconfigurations;

import java.util.HashMap;
import java.util.Map;

/**
 * Emulates Spring Boot Auto-Configuration conditional bean registration.
 */
public class AutoConfigurationEngine {

    public interface CacheClient {
        String type();
    }

    public static class DefaultMemoryCache implements CacheClient {
        @Override
        public String type() { return "IN_MEMORY"; }
    }

    public static class CustomRedisCache implements CacheClient {
        @Override
        public String type() { return "REDIS"; }
    }

    private final Map<Class<?>, Object> beanContext = new HashMap<>();

    public void registerUserBean(Class<?> type, Object instance) {
        beanContext.put(type, instance);
    }

    // Emulates @ConditionalOnMissingBean(CacheClient.class)
    public CacheClient autoConfigureCacheClient() {
        if (!beanContext.containsKey(CacheClient.class)) {
            DefaultMemoryCache fallback = new DefaultMemoryCache();
            beanContext.put(CacheClient.class, fallback);
            return fallback;
        }
        return (CacheClient) beanContext.get(CacheClient.class);
    }
}
