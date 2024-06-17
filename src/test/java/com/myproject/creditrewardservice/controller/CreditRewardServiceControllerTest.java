package com.myproject.creditrewardservice.controller;

import com.myproject.creditrewardservice.bean.CustomerInfo;
import com.myproject.creditrewardservice.entity.Customer;
import com.myproject.creditrewardservice.repository.CustomerRepository;
import com.myproject.creditrewardservice.service.impl.CustomerRewardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class CreditRewardServiceControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    CreditRewardServiceController creditRewardServiceController;

    @Mock
    CustomerRewardServiceImpl customerRewardService;
    @Mock
    CustomerRepository customerRepository;
    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(creditRewardServiceController).build();
    }

    @Test
    public void testCustomerFound() throws Exception {
        Customer customer = new Customer();
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerId(101);
        customerInfo.setCustomerName("Kamal");
        customerInfo.setPreviousMonthRewardPoints(50);
        customerInfo.setPreviousSecondMonthRewardPoints(100);
        customerInfo.setPreviousThirdMonthRewardPoints(200);
        customerInfo.setTotalRewardPoints(350);
        RequestBuilder requestBuilder = get("/creditRewardService/101/rewards");
        lenient().doReturn(customer).when(customerRewardService).getCustomer(any());
        lenient().doReturn(customerInfo).when(customerRewardService).getRewardsByCustomerId(any());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mvcResponse = mvcResult.getResponse();
        Assertions.assertTrue(mvcResponse.getContentAsString().contains("101"));
        Assertions.assertEquals(mvcResponse.getStatus(), 200);
    }
    @Test
    public void testCustomerBadRequest() throws Exception {
        RequestBuilder requestBuilder = get("/creditRewardService/105L/rewards");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(400,mvcResult.getResponse().getStatus());
    }
}
