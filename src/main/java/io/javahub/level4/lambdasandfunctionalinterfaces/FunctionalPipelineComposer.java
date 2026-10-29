package io.javahub.level4.lambdasandfunctionalinterfaces;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Functional pipelines built with standard java.util.function interfaces.
 */
public class FunctionalPipelineComposer {

    public static Function<String, String> createSanitizer() {
        Function<String, String> trim = String::trim;
        Function<String, String> lower = String::toLowerCase;
        Function<String, String> stripNonAlpha = s -> s.replaceAll("[^a-z0-9]", "");

        return trim.andThen(lower).andThen(stripNonAlpha);
    }

    public static Predicate<Integer> isBetween(int min, int max) {
        Predicate<Integer> greaterOrEqual = val -> val >= min;
        Predicate<Integer> lessOrEqual = val -> val <= max;
        return greaterOrEqual.and(lessOrEqual);
    }
}
