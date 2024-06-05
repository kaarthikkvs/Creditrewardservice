package com.myproject.creditrewardservice.controller;

import com.myproject.creditrewardservice.bean.Customer;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@Slf4j
public class CreditRewardServiceController {
    @PostMapping(path = "/creditRewardService/fetch")
    public ResponseEntity<Customer> getCreditRewardScore(@RequestBody Customer customer, WebRequest request) {
        ResponseEntity<Customer> customerResponseEntty = null;
        log.info("Customer: " + customer.getName());
        log.info("Customer ID: " + customer.getId());
        log.info("Customer Reward Score: " + customer.getRewardScore());

        customerResponseEntty = ResponseEntity.ok(customer);
        return customerResponseEntty;
    }
}
