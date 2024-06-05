package com.myproject.creditrewardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCreditrewardserviceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CreditrewardserviceApplication::main).with(TestCreditrewardserviceApplication.class).run(args);
    }

}
