package io.javahub.level7.hibernateperformancetuning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class QueryOptimizationGuideTest {

    @Test
    @DisplayName("Batch fetching should group children in 1 query avoiding N+1 roundtrips")
    void testBatchFetch() {
        var guide = new QueryOptimizationGuide();
        var children = List.of(
            new QueryOptimizationGuide.Child(1, 100L, "Child A"),
            new QueryOptimizationGuide.Child(2, 100L, "Child B"),
            new QueryOptimizationGuide.Child(3, 200L, "Child C")
        );

        var grouped = guide.batchFetchChildren(List.of(100L, 200L), children);

        assertThat(grouped.get(100L)).hasSize(2);
        assertThat(grouped.get(200L)).hasSize(1);
    }
}
