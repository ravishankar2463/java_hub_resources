package io.javahub.level4.reactiveprogrammingbasics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ReactiveStreamSimulatorTest {

    @Test
    @DisplayName("Publisher must honor subscriber request demand (backpressure)")
    void testBackpressureDemand() {
        List<String> events = List.of("e1", "e2", "e3", "e4", "e5");
        ReactiveStreamSimulator.SimplePublisher<String> publisher = new ReactiveStreamSimulator.SimplePublisher<>(events);

        List<String> received = new ArrayList<>();
        publisher.subscribe(new ReactiveStreamSimulator.Subscriber<>() {
            @Override
            public void onNext(String item) { received.add(item); }
            @Override
            public void onComplete() {}
        }, 2); // Request only 2 items

        assertThat(received).containsExactly("e1", "e2");
    }
}
