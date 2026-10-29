package io.javahub.level7.distributedcachingwithredis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: Distributed Caching with Redis.
 */
public class DistributedcachingwithredisLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        DistributedcachingwithredisLab lab = new DistributedcachingwithredisLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("Distributed Caching with Redis");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
