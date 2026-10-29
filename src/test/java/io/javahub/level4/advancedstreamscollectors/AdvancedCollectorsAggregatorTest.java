package io.javahub.level4.advancedstreamscollectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class AdvancedCollectorsAggregatorTest {

    private final AdvancedCollectorsAggregator aggregator = new AdvancedCollectorsAggregator();

    private final List<AdvancedCollectorsAggregator.Transaction> sampleData = List.of(
        new AdvancedCollectorsAggregator.Transaction("US", "USD", 500.0),
        new AdvancedCollectorsAggregator.Transaction("US", "USD", 300.0),
        new AdvancedCollectorsAggregator.Transaction("DE", "EUR", 700.0)
    );

    @Test
    @DisplayName("Should group and sum amounts per country")
    void testGroupingBy() {
        Map<String, Double> countrySums = aggregator.sumAmountByCountry(sampleData);

        assertThat(countrySums.get("US")).isEqualTo(800.0);
        assertThat(countrySums.get("DE")).isEqualTo(700.0);
    }

    @Test
    @DisplayName("Should partition transactions into high and low value sets")
    void testPartitioningBy() {
        var partitioned = aggregator.partitionHighValue(sampleData, 600.0);

        assertThat(partitioned.get(true)).hasSize(1);
        assertThat(partitioned.get(false)).hasSize(2);
    }
}
