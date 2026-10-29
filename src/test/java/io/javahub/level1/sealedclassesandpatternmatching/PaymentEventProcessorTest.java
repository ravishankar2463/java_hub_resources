package io.javahub.level1.sealedclassesandpatternmatching;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentEventProcessorTest {

    private final PaymentEventProcessor processor = new PaymentEventProcessor();

    @Test
    @DisplayName("Should deconstruct Authorized record via pattern matching")
    void testPatternDeconstruction() {
        var event = new PaymentEventProcessor.Authorized("TX-1", new BigDecimal("120.00"), "4242");
        String summary = processor.summarize(event);

        assertThat(summary).isEqualTo("Authorized $120.00 on card ending in 4242");
    }

    @Test
    @DisplayName("Should deconstruct Refunded record via pattern matching")
    void testRefundedDeconstruction() {
        var event = new PaymentEventProcessor.Refunded("TX-2", new BigDecimal("50.00"), "Damaged item");
        String summary = processor.summarize(event);

        assertThat(summary).isEqualTo("Refunded $50.00 due to: Damaged item");
    }
}
