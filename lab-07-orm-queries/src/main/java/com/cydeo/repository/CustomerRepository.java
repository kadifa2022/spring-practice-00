package com.cydeo.repository;

import com.cydeo.entity_model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Write a derived query to get  customer by id

    Optional<Customer> findById(Long id);
    // Write a JPQL query to get customer by email

    @Query("SELECT  c FROM Customer c WHERE  c.email=?1")
    Customer retrieveByCustomerEmail(String email);
}