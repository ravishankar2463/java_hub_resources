package com.javahub.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "customer_orders")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    public CustomerOrder() {}

    public CustomerOrder(String orderNumber, double totalAmount) {
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
    }

    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public double getTotalAmount() { return totalAmount; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
