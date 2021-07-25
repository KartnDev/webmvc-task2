package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
