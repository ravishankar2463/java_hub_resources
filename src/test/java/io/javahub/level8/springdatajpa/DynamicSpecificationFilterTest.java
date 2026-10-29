package io.javahub.level8.springdatajpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DynamicSpecificationFilterTest {

    @Test
    @DisplayName("Should compose dynamic filter specifications matching Spring Data JPA criteria")
    void testDynamicFilter() {
        var filter = new DynamicSpecificationFilter();
        var products = List.of(
            new DynamicSpecificationFilter.Product("P1", "TECH", 800.0, true),
            new DynamicSpecificationFilter.Product("P2", "TECH", 1500.0, true),
            new DynamicSpecificationFilter.Product("P3", "HOME", 100.0, false)
        );

        var matches = filter.filter(products, "TECH", 1000.0, true);
        assertThat(matches).hasSize(1);
        assertThat(matches.get(0).id()).isEqualTo("P1");
    }
}
