package io.javahub.level5.structuredconcurrencyandscopedvalues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StructuredScopeAggregatorTest {

    @Test
    @DisplayName("Should aggregate user profile results deterministically")
    void testAggregation() {
        StructuredScopeAggregator aggregator = new StructuredScopeAggregator();
        var profile = aggregator.fetchSynchronousAggregate("42");

        assertThat(profile.username()).isEqualTo("User_42");
        assertThat(profile.score()).isEqualTo(95);
    }
}
