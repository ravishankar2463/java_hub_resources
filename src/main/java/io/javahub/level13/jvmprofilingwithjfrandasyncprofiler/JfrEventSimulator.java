package io.javahub.level13.jvmprofilingwithjfrandasyncprofiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Emulates JDK Flight Recorder (JFR) custom event emission.
 */
public class JfrEventSimulator {

    public record JfrEvent(String name, long durationMillis) {}

    private final List<JfrEvent> recordedEvents = new ArrayList<>();

    public void emitEvent(String name, long durationMillis) {
        recordedEvents.add(new JfrEvent(name, durationMillis));
    }

    public List<JfrEvent> getRecordedEvents() { return List.copyOf(recordedEvents); }
}
