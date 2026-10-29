package io.javahub.level7.jpamandhibernatebasics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersistenceContextSimulatorTest {

    @Test
    @DisplayName("L1 cache should serve repeat queries and detect dirty modifications")
    void testL1CacheAndDirtyChecking() {
        var context = new PersistenceContextSimulator();
        context.persist("E1", "Initial Name");

        assertThat(context.find("E1")).isNotNull();
        assertThat(context.isDirty("E1", "Initial Name")).isFalse();
        assertThat(context.isDirty("E1", "Updated Name")).isTrue();
    }
}
