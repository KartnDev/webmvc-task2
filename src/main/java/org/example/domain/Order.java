package org.example.domain;

import lombok.*;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String userId;
    private String orderNumber;
    private Integer amount;
    private Integer currency;
    private String status;
    private String returnUrl;
    private String failUrl;
    private Boolean isDeleted;
    private Date deletedAt;
}
