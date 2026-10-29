package io.javahub.level2.moderncoreapis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ModernCoreCatalogTest {

    private final ModernCoreCatalog catalog = new ModernCoreCatalog();

    @Test
    @DisplayName("List.of must return strictly unmodifiable list")
    void testImmutableList() {
        List<ModernCoreCatalog.AuditLog> logs = catalog.createSampleLogs();
        assertThat(logs).hasSize(2);

        assertThatThrownBy(() -> logs.add(new ModernCoreCatalog.AuditLog("eve", Instant.now(), "HACK")))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Instant should convert accurately to specific time zone")
    void testZoneConversion() {
        Instant now = Instant.parse("2026-10-29T10:00:00Z");
        ZonedDateTime tokyoTime = catalog.convertToZone(now, "Asia/Tokyo");

        assertThat(tokyoTime.getHour()).isEqualTo(19); // UTC+9
    }
}
