package io.javahub.level10.kafkaconsumergroupsandrebalances;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class RebalancePartitionAssignorTest {

    @Test
    @DisplayName("Should evenly distribute partition assignments across consumer instances")
    void testAssignor() {
        var assignor = new RebalancePartitionAssignor();
        var assignment = assignor.assignPartitions(List.of("consumer_A", "consumer_B"), 4);

        assertThat(assignment.get("consumer_A")).containsExactly(0, 2);
        assertThat(assignment.get("consumer_B")).containsExactly(1, 3);
    }
}
