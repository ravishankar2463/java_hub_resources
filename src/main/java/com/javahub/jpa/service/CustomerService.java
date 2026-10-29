package com.javahub.jpa.service;

import com.javahub.jpa.entity.Customer;
import com.javahub.jpa.entity.CustomerOrder;
import com.javahub.jpa.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        Customer c1 = new Customer("Alice Johnson", "alice@corp.com", true);
        c1.addOrder(new CustomerOrder("ORD-101", 149.50));
        c1.addOrder(new CustomerOrder("ORD-102", 89.00));

        Customer c2 = new Customer("Bob Smith", "bob@corp.com", true);
        c2.addOrder(new CustomerOrder("ORD-201", 1250.00));

        Customer c3 = new Customer("Charlie Inactive", "charlie@corp.com", false);

        customerRepository.saveAll(List.of(c1, c2, c3));
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllActive() {
        return customerRepository.findByActiveTrue();
    }

    @Transactional(readOnly = true)
    public Optional<Customer> getWithOrders(Long id) {
        return customerRepository.findWithOrdersById(id);
    }
}
