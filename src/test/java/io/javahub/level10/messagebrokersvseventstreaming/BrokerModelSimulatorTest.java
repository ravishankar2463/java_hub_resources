package io.javahub.level10.messagebrokersvseventstreaming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BrokerModelSimulatorTest {

    @Test
    @DisplayName("Kafka ImmutableLog should preserve messages allowing multiple independent consumers to replay")
    void testImmutableLogReplay() {
        var log = new BrokerModelSimulator.ImmutableLog();
        int offset0 = log.append("event_A");
        int offset1 = log.append("event_B");

        // Consumer 1 reads
        assertThat(log.readAtOffset(offset0)).isEqualTo("event_A");

        // Consumer 2 reads the same message again (replayability)
        assertThat(log.readAtOffset(offset0)).isEqualTo("event_A");
        assertThat(log.totalMessages()).isEqualTo(2);
    }
}
