package com.example.customermanager3.service;

import com.example.customermanager3.model.Customer;
import com.example.customermanager3.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImplement implements CustomerService {

//    private static Map<Integer, Customer> customers;
//    static {
//        customers = new HashMap<>();
//        customers.put(1, new Customer(1, "John", "john@codegym.vn", "Hanoi"));
//        customers.put(2, new Customer(2, "Bill", "bill@codegym.vn", "Danang"));
//        customers.put(3, new Customer(3, "Alex", "alex@codegym.vn", "Saigon"));
//        customers.put(4, new Customer(4, "Adam", "adam@codegym.vn", "Beijin"));
//        customers.put(5, new Customer(5, "Sophia", "sophia@codegym.vn", "Miami"));
//        customers.put(6, new Customer(6, "Rose", "rose@codegym.vn", "Newyork"));
//    }
    @Autowired
    CustomerRepository repo;

    @Override
    public List<Customer> findAll() {
        return repo.findAll();
    }

    @Override
    public Customer save(Customer customer)  throws DuplicateEmailException{
        try {
            return repo.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Customer customer) {

    }


    @Override
    public void remove(int id) {

    }

    @Override
    public Page<Customer> findAll(Pageable pageable) throws Exception {
        if (true) throw new Exception("a dummy exception");
        return null;
    }

    @Override
    public Optional<Customer> findOne(int id) throws Exception {
        Optional<Customer> customerOptional = repo.findById(id);
        if (!customerOptional.isPresent()) {
            throw new Exception("customer not found!");
        }
        return customerOptional;
    }

//    @Override
//    public void save(Customer customer) {
//        customers.put(customer.getId(), customer);
//    }
//
//    @Override
//    public Customer findById(int id) {
//        return customers.get(id);
//    }
//
//    @Override
//    public void update(int id, Customer customer) {
//        customers.put(id, customer);
//    }
//
//    @Override
//    public void remove(int id) {
//        customers.remove(id);
//    }
}
