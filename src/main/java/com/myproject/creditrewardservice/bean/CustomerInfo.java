package com.myproject.creditrewardservice.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CustomerInfo {
    private long customerId;
    private String customerName;
    private long previousMonthRewardPoints;
    private long previousSecondMonthRewardPoints;
    private long previousThirdMonthRewardPoints;
    private long totalRewardPoints;
    private ErrorDetails errorDetails;


}
