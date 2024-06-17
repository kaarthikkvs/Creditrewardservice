package com.myproject.creditrewardservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "TRANSACTION")
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    @Column(name = "transaction_amount")
    private double transactionAmount;

}
