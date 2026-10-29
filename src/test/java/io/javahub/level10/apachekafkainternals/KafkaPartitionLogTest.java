package io.javahub.level10.apachekafkainternals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class KafkaPartitionLogTest {

    @Test
    @DisplayName("Consumers should only be permitted to read up to the High Watermark (HW)")
    void testKafkaWatermark() {
        var partition = new KafkaPartitionLog();
        partition.append("msg_0");
        partition.append("msg_1");
        partition.append("msg_2");

        assertThat(partition.getLeo()).isEqualTo(3L);

        // ISR replicates up to offset 2 (meaning 0 and 1 are committed)
        partition.updateHighWatermark(2L);

        var visible = partition.readCommitted(0L);
        assertThat(visible).hasSize(2);
        assertThat(visible.get(1).payload()).isEqualTo("msg_1");
    }
}
