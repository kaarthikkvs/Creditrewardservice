package com.myproject.creditrewardservice.service.impl;

import com.myproject.creditrewardservice.bean.CustomerInfo;
import com.myproject.creditrewardservice.entity.Customer;
import com.myproject.creditrewardservice.entity.Transaction;
import com.myproject.creditrewardservice.exception.ResourceNotFoundException;
import com.myproject.creditrewardservice.repository.CustomerRepository;
import com.myproject.creditrewardservice.repository.TransactionRepository;
import com.myproject.creditrewardservice.service.CustomerRewardService;
import com.myproject.creditrewardservice.utility.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerRewardServiceImpl implements CustomerRewardService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomer(Long customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {
            throw new ResourceNotFoundException("Invalid / Missing customer Id ");
        }
        return customer;
    }

    public CustomerInfo getRewardsByCustomerId(Long customerId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*Constants.daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*Constants.daysInMonths);

        log.info("Last Month Timestamp : "+lastMonthTimestamp);
        log.info("Last 2nd Month Timestamp : "+lastSecondMonthTimestamp);
        log.info("Last 3rd Month Timestamp : "+lastThirdMonthTimestamp);

        List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<Transaction> lastSecondMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<Transaction> lastThirdMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        CustomerInfo customerRewards = new CustomerInfo();
        customerRewards.setCustomerId(customerId);
        customerRewards.setPreviousMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setPreviousSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setPreviousThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewardPoints(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);
        return customerRewards;

    }

    public Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > Constants.firstRewardLimit && t.getTransactionAmount() <= Constants.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - Constants.firstRewardLimit);
        } else if (t.getTransactionAmount() > Constants.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - Constants.secondRewardLimit) * 2
                    + (Constants.secondRewardLimit - Constants.firstRewardLimit);
        } else
            return 0l;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

    public Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }
}
