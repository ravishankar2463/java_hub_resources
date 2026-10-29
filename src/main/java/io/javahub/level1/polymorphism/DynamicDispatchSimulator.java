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
}
