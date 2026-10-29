package io.javahub.level3.generics;

import java.util.Collection;
import java.util.List;

/**
 * Demonstrates PECS: Producer Extends, Consumer Super for flexible generic APIs.
 */
public class PecsGenericProcessor {

    // Producer Extends: Reads from source (source produces numbers)
    public double sumOfList(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // Consumer Super: Writes into destination (destination consumes integers)
    public void appendIntegers(Collection<? super Integer> destination, int count) {
        for (int i = 1; i <= count; i++) {
            destination.add(i);
        }
    }
}
