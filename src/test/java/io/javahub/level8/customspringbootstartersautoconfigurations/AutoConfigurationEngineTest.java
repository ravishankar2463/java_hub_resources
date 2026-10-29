package io.javahub.level8.customspringbootstartersautoconfigurations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AutoConfigurationEngineTest {

    @Test
    @DisplayName("Should auto-configure DefaultMemoryCache when user bean is absent")
    void testConditionalFallback() {
        var engine = new AutoConfigurationEngine();
        var client = engine.autoConfigureCacheClient();

        assertThat(client.type()).isEqualTo("IN_MEMORY");
    }

    @Test
    @DisplayName("Should preserve user-provided bean and not overwrite it with default")
    void testUserBeanPrecedence() {
        var engine = new AutoConfigurationEngine();
        engine.registerUserBean(AutoConfigurationEngine.CacheClient.class, new AutoConfigurationEngine.CustomRedisCache());

        var client = engine.autoConfigureCacheClient();
        assertThat(client.type()).isEqualTo("REDIS");
    }
}
