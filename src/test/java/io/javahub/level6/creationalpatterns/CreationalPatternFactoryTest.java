package io.javahub.level6.creationalpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CreationalPatternFactoryTest {

    @Test
    @DisplayName("Object Pool should lend resources and restore count on return")
    void testObjectPool() throws Exception {
        var pool = new CreationalPatternFactory.TransportPool(2);
        assertThat(pool.availableCount()).isEqualTo(2);

        var t1 = pool.borrowTransport();
        assertThat(pool.availableCount()).isEqualTo(1);
        assertThat(t1.deliver()).contains("autonomous drone");

        pool.returnTransport(t1);
        assertThat(pool.availableCount()).isEqualTo(2);
    }
}
