package io.javahub.level1.compositionoverinheritance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class InstrumentedSetTest {

    @Test
    @DisplayName("InstrumentedSet must accurately track additions without double-counting on addAll")
    void testAccurateAddCount() {
        InstrumentedSet<String> set = new InstrumentedSet<>(new HashSet<>());
        set.addAll(List.of("Snap", "Crackle", "Pop"));

        assertThat(set.getAddCount()).isEqualTo(3);
        assertThat(set.size()).isEqualTo(3);
    }
}
