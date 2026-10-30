package com.javahub.basics.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "accounts")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String owner;
    private double balance;
    public Account() {}
    public Account(String owner, double balance) { this.owner = owner; this.balance = balance; }
    public Long getId() { return id; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
