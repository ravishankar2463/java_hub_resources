package com.javahub.sql.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "employee_salaries")
public class EmployeeSalary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name;
    private String department;
    private double salary;
    public EmployeeSalary() {}
    public EmployeeSalary(String name, String department, double salary) {
        this.name = name; this.department = department; this.salary = salary;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}
