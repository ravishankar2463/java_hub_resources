package io.javahub.level4.lambdasandfunctionalinterfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.function.Function;
import java.util.function.Predicate;
import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalPipelineComposerTest {

    @Test
    @DisplayName("Should compose string transformation functions with andThen()")
    void testFunctionComposition() {
        Function<String, String> pipeline = FunctionalPipelineComposer.createSanitizer();
        String result = pipeline.apply("  Java_Hub-2026!  ");

        assertThat(result).isEqualTo("javahub2026");
    }

    @Test
    @DisplayName("Predicate.and() should validate within range")
    void testPredicateChaining() {
        Predicate<Integer> validator = FunctionalPipelineComposer.isBetween(10, 20);

        assertThat(validator.test(15)).isTrue();
        assertThat(validator.test(9)).isFalse();
        assertThat(validator.test(21)).isFalse();
    }
}
