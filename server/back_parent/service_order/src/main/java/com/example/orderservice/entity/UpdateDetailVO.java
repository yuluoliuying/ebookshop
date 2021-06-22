package com.example.orderservice.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateDetailVO {

    private String orderId;

    private Integer orderMount;

    private BigDecimal unitPrice;

    private Long userId;

    private Long bookId;
}
