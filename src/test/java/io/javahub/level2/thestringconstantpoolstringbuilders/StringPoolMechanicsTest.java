package io.javahub.level2.thestringconstantpoolstringbuilders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StringPoolMechanicsTest {

    private final StringPoolMechanics mechanics = new StringPoolMechanics();

    @Test
    @DisplayName("String literals must point to the same String Constant Pool address")
    void testPoolIdentity() {
        assertThat(mechanics.testStringLiteralIdentity()).isTrue();
    }

    @Test
    @DisplayName("intern() must return pooled canonical string reference")
    void testIntern() {
        assertThat(mechanics.testInternBehavior()).isTrue();
    }

    @Test
    @DisplayName("StringBuilder should assemble CSV without truncation")
    void testStringBuilder() {
        String csv = mechanics.buildConcatenatedCsv(3);
        assertThat(csv).isEqualTo("id_0,id_1,id_2");
    }
}
