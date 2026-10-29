package io.javahub.level8.springcoreinternals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpringLifecycleContainerTest {

    @Test
    @DisplayName("TransactionalProxyFactory should intercept and execute bean methods through dynamic proxy")
    void testDynamicProxyWrapping() {
        var rawService = new SpringLifecycleContainer.OrderServiceImpl();
        var proxied = SpringLifecycleContainer.TransactionalProxyFactory.wrapInProxy(
            SpringLifecycleContainer.OrderService.class, rawService
        );

        String result = proxied.processOrder("ORD-101");
        assertThat(result).isEqualTo("ORDER_PROCESSED: ORD-101");
    }
}
