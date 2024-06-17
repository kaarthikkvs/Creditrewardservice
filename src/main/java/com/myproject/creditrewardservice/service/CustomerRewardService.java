package com.myproject.creditrewardservice.service;

import com.myproject.creditrewardservice.bean.CustomerInfo;
import com.myproject.creditrewardservice.entity.Customer;
import com.myproject.creditrewardservice.entity.Transaction;

public interface CustomerRewardService {
    Customer getCustomer(Long customerId);
    CustomerInfo getRewardsByCustomerId(Long customerId);
    Long calculateRewards(Transaction transaction);
}
