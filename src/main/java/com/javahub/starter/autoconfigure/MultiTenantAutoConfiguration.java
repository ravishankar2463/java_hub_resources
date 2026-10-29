package com.javahub.starter.autoconfigure;

import com.javahub.starter.properties.MultiTenantProperties;
import com.javahub.starter.service.TenantContextService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(prefix = "app.multitenant", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(MultiTenantProperties.class)
public class MultiTenantAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TenantContextService tenantContextService(MultiTenantProperties properties) {
        return new TenantContextService(properties);
    }
}
