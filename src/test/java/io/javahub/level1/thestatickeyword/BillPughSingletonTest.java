package io.javahub.level1.thestatickeyword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BillPughSingletonTest {

    @Test
    @DisplayName("Multiple calls to getInstance() must yield the exact same heap reference")
    void testSingletonIdentity() {
        BillPughSingleton first = BillPughSingleton.getInstance();
        BillPughSingleton second = BillPughSingleton.getInstance();

        assertThat(first).isSameAs(second);
        assertThat(BillPughSingleton.getTotalAllocations()).isEqualTo(1L);
    }
}
