package com.javahub.sql.service;
import com.javahub.sql.entity.EmployeeSalary;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
public class WindowFunctionService {
    private final EntityManager em;
    public WindowFunctionService(EntityManager em) { this.em = em; }
    @PostConstruct
    @Transactional
    public void seed() {
        em.persist(new EmployeeSalary("Alice", "Engineering", 145000));
        em.persist(new EmployeeSalary("Bob", "Engineering", 125000));
        em.persist(new EmployeeSalary("Charlie", "Engineering", 160000));
        em.persist(new EmployeeSalary("Diana", "Marketing", 95000));
        em.persist(new EmployeeSalary("Evan", "Marketing", 110000));
    }
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getRankedSalariesByDepartment() {
        String sql = """
            SELECT name, department, salary,
                   DENSE_RANK() OVER (PARTITION BY department ORDER BY salary DESC) as dept_rank,
                   AVG(salary) OVER (PARTITION BY department) as dept_avg
            FROM employee_salaries
            ORDER BY department, dept_rank
        """;
        List<?> rows = em.createNativeQuery(sql).getResultList();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object rowObj : rows) {
            Object[] row = (Object[]) rowObj;
            result.add(Map.of(
                "name", row[0],
                "department", row[1],
                "salary", row[2],
                "rank", row[3],
                "departmentAverage", row[4]
            ));
        }
        return result;
    }
}
