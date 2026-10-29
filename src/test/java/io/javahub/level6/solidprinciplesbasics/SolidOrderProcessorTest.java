package io.javahub.level6.solidprinciplesbasics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class SolidOrderProcessorTest {

    @Test
    @DisplayName("Should process order total using injected TaxStrategy and notify listeners")
    void testSolidProcessing() {
        List<String> auditLog = new ArrayList<>();
        SolidOrderProcessor.NotificationService notifier = auditLog::add;
        SolidOrderProcessor.TaxStrategy vat = new SolidOrderProcessor.StandardVatTax();

        SolidOrderProcessor processor = new SolidOrderProcessor(vat, notifier);
        BigDecimal total = processor.processTotal(new BigDecimal("100.00"));

        assertThat(total).isEqualByComparingTo("120.00");
        assertThat(auditLog).hasSize(1);
        assertThat(auditLog.get(0)).contains("Processed order total: $120.00");
    }
}
