package io.javahub.level7.distributedlockingandcachepatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DistributedLockManagerTest {

    @Test
    @DisplayName("Should enforce mutual exclusion and verify owner token upon release")
    void testDistributedLock() {
        var manager = new DistributedLockManager();

        assertThat(manager.tryAcquire("order_42", "node_A")).isTrue();
        assertThat(manager.tryAcquire("order_42", "node_B")).isFalse();

        // Node B cannot release Node A's lock
        assertThat(manager.release("order_42", "node_B")).isFalse();

        // Node A releases successfully
        assertThat(manager.release("order_42", "node_A")).isTrue();
        assertThat(manager.tryAcquire("order_42", "node_B")).isTrue();
    }
}
