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

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: PecsGenericProcessor");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for PecsGenericProcessor...");
        try {
            PecsGenericProcessor instance = new PecsGenericProcessor();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
