package io.javahub.level2.networksocketprogramming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class EchoProtocolSimulatorTest {

    @Test
    @DisplayName("Should simulate TCP echo server protocol stream transformation")
    void testEchoProtocol() throws IOException {
        EchoProtocolSimulator simulator = new EchoProtocolSimulator();
        String response = simulator.processEchoStream("PING");

        assertThat(response).isEqualTo("ECHO: PING");
    }
}
