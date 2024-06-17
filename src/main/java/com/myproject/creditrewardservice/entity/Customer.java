package com.myproject.creditrewardservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CUSTOMER")
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "customer_name")
    private String customerName;

}
