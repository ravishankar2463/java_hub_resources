package io.javahub.level6.behavioralpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Behavioral Observer and Strategy patterns.
 */
public class BehavioralWorkflowSuite {

    public interface EventListener {
        void onEvent(String eventName);
    }

    public static class EventBroadcaster {
        private final List<EventListener> listeners = new ArrayList<>();

        public void subscribe(EventListener listener) {
            listeners.add(listener);
        }

        public void broadcast(String eventName) {
            for (EventListener l : listeners) {
                l.onEvent(eventName);
            }
        }
    }
}
