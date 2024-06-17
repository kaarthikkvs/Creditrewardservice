package com.myproject.creditrewardservice.service;

import com.myproject.creditrewardservice.bean.CustomerInfo;
import com.myproject.creditrewardservice.entity.Transaction;
import com.myproject.creditrewardservice.repository.TransactionRepository;
import com.myproject.creditrewardservice.service.impl.CustomerRewardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class CustomerRewardServiceImplTest {
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    @Spy
    CustomerRewardServiceImpl customerRewardService;

    @Test
    public void testGetRewardsByCustomerId() {
        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        Timestamp firstDate = new Timestamp(1715709902);
        Timestamp secondDate = new Timestamp(1713117902);
        Timestamp thirdDate = new Timestamp(1710439502);
        Long customerId= 101L;
        List<Transaction> prevMonthtransactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(120);
        transaction.setCustomerId(customerId);
        transaction.setTransactionId(1001L);
        transaction.setTransactionDate(firstDate);
        prevMonthtransactions.add(transaction);
        List<Transaction> secondMonthTransactions = new ArrayList<>();
        Transaction secondTransaction = new Transaction();
        secondTransaction.setTransactionAmount(120);
        secondTransaction.setCustomerId(customerId);
        secondTransaction.setTransactionId(1002L);
        secondTransaction.setTransactionDate(secondDate);
        secondMonthTransactions.add(secondTransaction);
        List<Transaction> thirdMonthTransactions = new ArrayList<>();
        Transaction thirdMonthTransaction = new Transaction();
        thirdMonthTransaction.setTransactionAmount(120);
        thirdMonthTransaction.setCustomerId(customerId);
        thirdMonthTransaction.setTransactionId(1003L);
        thirdMonthTransaction.setTransactionDate(thirdDate);
        thirdMonthTransactions.add(thirdMonthTransaction);
        lenient().doReturn(prevMonthtransactions).when(transactionRepository).findAllByCustomerIdAndTransactionDateBetween(customerId, firstDate, currentTimestamp);
        lenient().doReturn(secondMonthTransactions).when(transactionRepository).findAllByCustomerIdAndTransactionDateBetween(customerId, secondDate, firstDate);
        lenient().doReturn(thirdMonthTransactions).when(transactionRepository).findAllByCustomerIdAndTransactionDateBetween(customerId, thirdDate, secondDate);
        CustomerInfo customerInfo = customerRewardService.getRewardsByCustomerId(transaction.getCustomerId());
        Assertions.assertEquals(customerId, customerInfo.getCustomerId());
    }

    @Test
    public void testCalculateRewards() {
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(90);
        transaction.setCustomerId(101L);
        transaction.setTransactionId(1001L);
        transaction.setTransactionDate(new Timestamp(1715709902));
        Assertions.assertEquals(40L,customerRewardService.calculateRewards(transaction));

    }
}
