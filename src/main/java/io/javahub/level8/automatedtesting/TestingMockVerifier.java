package io.javahub.level8.automatedtesting;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Test double and verification spy modeling Mockito @MockBean interactions.
 */
public class TestingMockVerifier {

    public interface EmailGateway {
        void sendEmail(String to, String body);
    }

    public static class EmailGatewaySpy implements EmailGateway {
        private final AtomicInteger sendCount = new AtomicInteger(0);

        @Override
        public void sendEmail(String to, String body) {
            sendCount.incrementAndGet();
        }

        public int getSendCount() { return sendCount.get(); }
    }

    public static class NotificationService {
        private final EmailGateway gateway;

        public NotificationService(EmailGateway gateway) {
            this.gateway = gateway;
        }

        public void notifyUser(String user) {
            gateway.sendEmail(user + "@javahub.io", "Welcome!");
        }
    }
}
