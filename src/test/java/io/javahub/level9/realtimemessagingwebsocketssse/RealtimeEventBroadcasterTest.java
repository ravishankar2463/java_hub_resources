package io.javahub.level9.realtimemessagingwebsocketssse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RealtimeEventBroadcasterTest {

    @Test
    @DisplayName("SSE event should format into RFC compliant text/event-stream wire chunk")
    void testSseFormatting() {
        var broadcaster = new RealtimeEventBroadcaster();
        var event = broadcaster.emitEvent("PRICE_UPDATE", "{\"BTC\": 95000}");

        assertThat(event.formatAsSseWire()).isEqualTo("id:1\nevent:PRICE_UPDATE\ndata:{\"BTC\": 95000}\n\n");
    }
}
