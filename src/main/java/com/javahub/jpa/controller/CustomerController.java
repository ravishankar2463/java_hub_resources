package com.javahub.jpa.controller;

import com.javahub.jpa.entity.Customer;
import com.javahub.jpa.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/active")
    public ResponseEntity<List<Customer>> getActiveCustomers() {
        return ResponseEntity.ok(customerService.getAllActive());
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<Customer> getCustomerWithOrders(@PathVariable Long id) {
        return customerService.getWithOrders(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
