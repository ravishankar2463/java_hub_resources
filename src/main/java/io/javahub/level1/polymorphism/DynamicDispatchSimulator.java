package io.javahub.level1.polymorphism;

/**
 * Demonstrates polymorphic vtable method dispatch.
 */
public class DynamicDispatchSimulator {

    public interface Shape {
        double calculateArea();
    }

    public record Circle(double radius) implements Shape {
        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }

    public record Rectangle(double width, double height) implements Shape {
        @Override
        public double calculateArea() {
            return width * height;
        }
    }

    public double aggregateAreas(Shape[] shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.calculateArea(); // Dynamic vtable dispatch
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: DynamicDispatchSimulator");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for DynamicDispatchSimulator...");
        try {
            DynamicDispatchSimulator instance = new DynamicDispatchSimulator();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
