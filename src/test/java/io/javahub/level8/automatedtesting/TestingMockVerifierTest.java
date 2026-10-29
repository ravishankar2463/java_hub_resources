package io.javahub.level8.automatedtesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestingMockVerifierTest {

    @Test
    @DisplayName("Spy should verify interaction count on collaborating service")
    void testMockInteraction() {
        var spy = new TestingMockVerifier.EmailGatewaySpy();
        var service = new TestingMockVerifier.NotificationService(spy);

        service.notifyUser("alice");
        service.notifyUser("bob");

        assertThat(spy.getSendCount()).isEqualTo(2);
    }
}
