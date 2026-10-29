package io.javahub.level3.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PecsGenericProcessorTest {

    private final PecsGenericProcessor processor = new PecsGenericProcessor();

    @Test
    @DisplayName("Producer Extends should accept List<Integer> and List<Double>")
    void testProducerExtends() {
        List<Integer> ints = List.of(1, 2, 3);
        List<Double> doubles = List.of(1.5, 2.5);

        assertThat(processor.sumOfList(ints)).isEqualTo(6.0);
        assertThat(processor.sumOfList(doubles)).isEqualTo(4.0);
    }

    @Test
    @DisplayName("Consumer Super should allow writing integers into Collection<Number> or Collection<Object>")
    void testConsumerSuper() {
        List<Number> numberList = new ArrayList<>();
        processor.appendIntegers(numberList, 3);

        assertThat(numberList).containsExactly(1, 2, 3);
    }
}
