package org.example.domain.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRegisterRequest {
    private String userName;
    private String password;
    private String orderNumber;
    private Integer amount;
    private Integer currency;
    private String returnUrl;
    private String failUrl;
}
