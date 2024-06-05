package com.myproject.creditrewardservice.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    private int id;
    private String name;

    private String rewardScore;


}
