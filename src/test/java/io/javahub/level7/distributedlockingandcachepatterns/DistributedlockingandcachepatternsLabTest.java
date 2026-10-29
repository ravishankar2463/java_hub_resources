package io.javahub.level7.distributedlockingandcachepatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: Distributed Locking and Cache Patterns.
 */
public class DistributedlockingandcachepatternsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        DistributedlockingandcachepatternsLab lab = new DistributedlockingandcachepatternsLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("Distributed Locking and Cache Patterns");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
