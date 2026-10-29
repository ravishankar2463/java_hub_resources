package io.javahub.level6.structuralpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StructuralAdapterSuiteTest {

    @Test
    @DisplayName("Decorator should wrap message sender and transparently count invocations")
    void testDecorator() {
        var base = new StructuralAdapterSuite.DefaultSender();
        var decorator = new StructuralAdapterSuite.CountingSenderDecorator(base);

        String out1 = decorator.send("Hello");
        String out2 = decorator.send("World");

        assertThat(out1).isEqualTo("SENT: Hello");
        assertThat(out2).isEqualTo("SENT: World");
        assertThat(decorator.getInvocationCount()).isEqualTo(2);
    }
}
