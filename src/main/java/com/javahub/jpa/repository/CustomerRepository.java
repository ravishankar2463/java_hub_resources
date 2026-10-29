package com.javahub.jpa.repository;

import com.javahub.jpa.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @EntityGraph(attributePaths = {"orders"})
    Optional<Customer> findWithOrdersById(Long id);

    List<Customer> findByActiveTrue();
}
