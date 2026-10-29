package io.javahub.level1.abstraction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentGatewayTest {

    @Test
    @DisplayName("Should use default method implementation and static factory method")
    void testGatewayDefaultAndStatic() {
        PaymentGateway gateway = PaymentGateway.createMockGateway(true);

        boolean result = gateway.processPayment("TX-999", new BigDecimal("150.00"));
        String receipt = gateway.generateReceipt("TX-999", new BigDecimal("150.00"));

        assertThat(result).isTrue();
        assertThat(receipt).contains("Receipt for Tx: TX-999");
    }
}
