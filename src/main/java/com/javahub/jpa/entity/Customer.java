package com.javahub.jpa.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerOrder> orders = new ArrayList<>();

    public Customer() {}

    public Customer(String name, String email, boolean active) {
        this.name = name;
        this.email = email;
        this.active = active;
    }

    public void addOrder(CustomerOrder order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public List<CustomerOrder> getOrders() { return orders; }
}
