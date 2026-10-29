package io.javahub.level11.apigatewaysandservicediscovery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApiGatewayRouteMatcherTest {

    @Test
    @DisplayName("Should route matched path round-robin across registered upstream pods")
    void testGatewayRouting() {
        var gateway = new ApiGatewayRouteMatcher();

        String url1 = gateway.routeToNextInstance("/api/orders/1");
        String url2 = gateway.routeToNextInstance("/api/orders/2");

        assertThat(url1).isEqualTo("http://10.0.1.5:8080/api/orders/1");
        assertThat(url2).isEqualTo("http://10.0.1.6:8080/api/orders/2");
    }

    @Test
    @DisplayName("Should reject path not matching gateway predicate")
    void testUnmatchedRoute() {
        var gateway = new ApiGatewayRouteMatcher();
        assertThatThrownBy(() -> gateway.routeToNextInstance("/api/unknown"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
