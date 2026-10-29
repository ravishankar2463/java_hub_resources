package io.javahub.level1.memoryreferencescopying;

import java.util.Date;

/**
 * Proof of Java 100% pass-by-value semantics and defensive copy patterns.
 */
public class PassByValueProof {

    public static class Car {
        private String color;
        public Car(String color) { this.color = color; }
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
    }

    // Reassignment does NOT affect caller reference
    public static void attemptReassign(Car c) {
        c = new Car("Yellow");
    }

    // Internal mutation DOES affect caller object on heap
    public static void mutateColor(Car c, String newColor) {
        c.setColor(newColor);
    }

    // Defensive copy protecting internal mutable state
    public static final class SecurePeriod {
        private final Date start;
        private final Date end;

        public SecurePeriod(Date start, Date end) {
            // Defensive copy on entry
            this.start = new Date(start.getTime());
            this.end = new Date(end.getTime());
            if (this.start.after(this.end)) {
                throw new IllegalArgumentException("Start after end");
            }
        }

        public Date getStart() {
            // Defensive copy on exit
            return new Date(start.getTime());
        }
    }
}
