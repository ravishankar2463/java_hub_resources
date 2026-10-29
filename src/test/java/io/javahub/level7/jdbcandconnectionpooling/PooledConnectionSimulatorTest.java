package io.javahub.level7.jdbcandconnectionpooling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PooledConnectionSimulatorTest {

    @Test
    @DisplayName("Pool should lease and reclaim connections")
    void testPoolLeaseAndRelease() throws Exception {
        var pool = new PooledConnectionSimulator();
        assertThat(pool.availableCount()).isEqualTo(5);

        var conn = pool.lease();
        assertThat(pool.availableCount()).isEqualTo(4);

        pool.release(conn);
        assertThat(pool.availableCount()).isEqualTo(5);
    }
}
