package io.javahub.level1.inheritance;

import java.math.BigDecimal;

/**
 * Demonstrates inheritance, constructor chaining, and polymorphic compensation calculations.
 */
public class EmployeeHierarchy {

    public static class Employee {
        private final String name;
        private final BigDecimal baseSalary;

        public Employee(String name, BigDecimal baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }

        public BigDecimal calculateTotalPay() {
            return baseSalary;
        }

        public String getName() { return name; }
        public BigDecimal getBaseSalary() { return baseSalary; }
    }

    public static class Manager extends Employee {
        private final BigDecimal bonus;

        public Manager(String name, BigDecimal baseSalary, BigDecimal bonus) {
            super(name, baseSalary);
            this.bonus = bonus;
        }

        @Override
        public BigDecimal calculateTotalPay() {
            return getBaseSalary().add(bonus);
        }

        public BigDecimal getBonus() { return bonus; }
    }
}
