package com.example.customermanager3.service;

import com.example.customermanager3.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Customer save(Customer customer) throws DuplicateEmailException;
    Customer findById(int id);
    void update(int id, Customer customer);
    void remove(int id);
    Page<Customer> findAll(Pageable pageable) throws Exception;
    Optional<Customer> findOne(int id) throws Exception;

}
