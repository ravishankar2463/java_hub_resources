package com.javahub.starter.service;

import com.javahub.starter.properties.MultiTenantProperties;

public class TenantContextService {

    private final MultiTenantProperties properties;
    private final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public TenantContextService(MultiTenantProperties properties) {
        this.properties = properties;
    }

    public String resolveCurrentTenant() {
        String t = currentTenant.get();
        return t != null ? t : properties.getDefaultTenant();
    }

    public void switchTenant(String tenantId) {
        currentTenant.set(tenantId);
    }

    public String getIsolationMode() {
        return properties.getIsolationMode();
    }
}
