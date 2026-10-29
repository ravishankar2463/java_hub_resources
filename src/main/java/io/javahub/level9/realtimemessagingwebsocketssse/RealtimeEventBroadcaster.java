package io.javahub.level9.realtimemessagingwebsocketssse;

import java.util.ArrayList;
import java.util.List;

/**
 * Broadcasts real-time events to connected SSE and WebSocket clients.
 */
public class RealtimeEventBroadcaster {

    public record SseEvent(String id, String event, String data) {
        public String formatAsSseWire() {
            return "id:" + id + "\nevent:" + event + "\ndata:" + data + "\n\n";
        }
    }

    private final List<SseEvent> eventHistory = new ArrayList<>();

    public SseEvent emitEvent(String eventName, String payload) {
        SseEvent event = new SseEvent(String.valueOf(eventHistory.size() + 1), eventName, payload);
        eventHistory.add(event);
        return event;
    }

    public List<SseEvent> getHistory() { return List.copyOf(eventHistory); }
}
