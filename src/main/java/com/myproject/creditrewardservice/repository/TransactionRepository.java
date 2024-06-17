package com.myproject.creditrewardservice.repository;

import com.myproject.creditrewardservice.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findAllByCustomerId(Long customerId);
    List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp start, Timestamp end);
}
