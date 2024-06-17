package com.myproject.creditrewardservice.bean;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorDetails {
    private String errorCode;
    private String errorMessage;
}
