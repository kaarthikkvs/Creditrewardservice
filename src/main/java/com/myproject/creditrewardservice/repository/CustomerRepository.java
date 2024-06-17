package com.myproject.creditrewardservice.repository;

import com.myproject.creditrewardservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByCustomerId(Long customerId);
}
