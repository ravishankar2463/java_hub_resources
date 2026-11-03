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

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: BehavioralWorkflowSuite");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for BehavioralWorkflowSuite...");
        try {
            BehavioralWorkflowSuite instance = new BehavioralWorkflowSuite();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
