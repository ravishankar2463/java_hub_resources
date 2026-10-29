package io.javahub.level1.inheritance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeHierarchyTest {

    @Test
    @DisplayName("Subclass should correctly invoke super constructor and override polymorphic pay calculation")
    void testPolymorphicPay() {
        EmployeeHierarchy.Employee emp = new EmployeeHierarchy.Employee("John", new BigDecimal("50000.00"));
        EmployeeHierarchy.Employee mgr = new EmployeeHierarchy.Manager("Sarah", new BigDecimal("80000.00"), new BigDecimal("20000.00"));

        assertThat(emp.calculateTotalPay()).isEqualByComparingTo("50000.00");
        assertThat(mgr.calculateTotalPay()).isEqualByComparingTo("100000.00");
    }
}
