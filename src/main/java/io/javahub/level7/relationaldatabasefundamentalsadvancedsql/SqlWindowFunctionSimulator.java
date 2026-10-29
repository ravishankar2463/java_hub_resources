package io.javahub.level7.relationaldatabasefundamentalsadvancedsql;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Emulates SQL analytical window functions over partitioned in-memory result sets.
 */
public class SqlWindowFunctionSimulator {

    public record EmployeeSalary(String department, String employee, double salary) {}
    public record RankedEmployee(String department, String employee, double salary, int rowNumber, double runningTotal) {}

    public List<RankedEmployee> executeWindowPartition(List<EmployeeSalary> dataset) {
        Map<String, List<EmployeeSalary>> byDept = dataset.stream()
            .collect(Collectors.groupingBy(EmployeeSalary::department));

        List<RankedEmployee> results = new ArrayList<>();

        for (Map.Entry<String, List<EmployeeSalary>> entry : byDept.entrySet()) {
            List<EmployeeSalary> sorted = entry.getValue().stream()
                .sorted(Comparator.comparingDouble(EmployeeSalary::salary).reversed())
                .toList();

            double runningTotal = 0.0;
            int rowNum = 1;
            for (EmployeeSalary emp : sorted) {
                runningTotal += emp.salary();
                results.add(new RankedEmployee(emp.department(), emp.employee(), emp.salary(), rowNum++, runningTotal));
            }
        }
        return results;
    }
}
