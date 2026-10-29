package io.javahub.level7.relationaldatabasefundamentalsadvancedsql;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class SqlWindowFunctionSimulatorTest {

    @Test
    @DisplayName("Should execute window partition, calculate row numbers, and accumulate running totals")
    void testWindowFunctions() {
        var sim = new SqlWindowFunctionSimulator();
        var dataset = List.of(
            new SqlWindowFunctionSimulator.EmployeeSalary("ENG", "Alice", 120000.0),
            new SqlWindowFunctionSimulator.EmployeeSalary("ENG", "Bob", 150000.0),
            new SqlWindowFunctionSimulator.EmployeeSalary("HR", "Charlie", 90000.0)
        );

        var ranked = sim.executeWindowPartition(dataset);
        var engRanked = ranked.stream().filter(r -> r.department().equals("ENG")).toList();

        assertThat(engRanked).hasSize(2);
        assertThat(engRanked.get(0).employee()).isEqualTo("Bob");
        assertThat(engRanked.get(0).rowNumber()).isEqualTo(1);
        assertThat(engRanked.get(1).runningTotal()).isEqualTo(270000.0);
    }
}
