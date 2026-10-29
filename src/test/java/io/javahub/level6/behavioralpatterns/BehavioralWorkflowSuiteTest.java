package io.javahub.level6.behavioralpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BehavioralWorkflowSuiteTest {

    @Test
    @DisplayName("Observer pattern should deliver events to all registered listeners")
    void testObserverBroadcast() {
        var broadcaster = new BehavioralWorkflowSuite.EventBroadcaster();
        List<String> audit1 = new ArrayList<>();
        List<String> audit2 = new ArrayList<>();

        broadcaster.subscribe(audit1::add);
        broadcaster.subscribe(audit2::add);

        broadcaster.broadcast("ORDER_PAID");

        assertThat(audit1).containsExactly("ORDER_PAID");
        assertThat(audit2).containsExactly("ORDER_PAID");
    }
}
