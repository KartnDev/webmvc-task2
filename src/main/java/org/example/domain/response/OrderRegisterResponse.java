package org.example.domain.response;

import lombok.*;
import org.example.domain.Order;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRegisterResponse {
    private Order createdOrder;
    private String status;
    private Date requestTimestamp;
    private String errorMessage;
    private Integer errorCode;
    private Boolean hasErrors;
}
