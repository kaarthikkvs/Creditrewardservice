package com.myproject.creditrewardservice.controller;

import com.myproject.creditrewardservice.bean.CustomerInfo;
import com.myproject.creditrewardservice.bean.ErrorDetails;
import com.myproject.creditrewardservice.exception.ResourceNotFoundException;
import com.myproject.creditrewardservice.repository.CustomerRepository;
import com.myproject.creditrewardservice.repository.TransactionRepository;
import com.myproject.creditrewardservice.service.CustomerRewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.myproject.creditrewardservice.utility.Constants.INTERNAL_SERVER_ERROR;
import static com.myproject.creditrewardservice.utility.Constants.RESOURCE_NOT_FOUND;

@RestController
@RequestMapping(path = "/creditRewardService")
@Slf4j
public class CreditRewardServiceController {
    @Autowired
    CustomerRewardService customerRewardService;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerInfo> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        CustomerInfo customerRewards = new CustomerInfo();
        ErrorDetails errorDetails = new ErrorDetails();
        try {
            customerRewardService.getCustomer(customerId);
            customerRewards = customerRewardService.getRewardsByCustomerId(customerId);
            return new ResponseEntity<>(customerRewards, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            errorDetails.setErrorCode(RESOURCE_NOT_FOUND);
            errorDetails.setErrorMessage("Customer not found");
            customerRewards.setErrorDetails(errorDetails);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerRewards);
        } catch (Exception e) {
            errorDetails.setErrorCode(INTERNAL_SERVER_ERROR);
            errorDetails.setErrorMessage("Process Error");
            customerRewards.setErrorDetails(errorDetails);
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerRewards);
    }
}
