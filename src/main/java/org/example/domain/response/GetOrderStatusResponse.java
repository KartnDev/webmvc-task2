package org.example.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderStatusResponse {
    private String orderStatus;
    private Integer errorCode;
    private String errorMessage;
    private Boolean hasErrors;
    private String orderId;
    private Integer amount;
    private Integer currency;
}
