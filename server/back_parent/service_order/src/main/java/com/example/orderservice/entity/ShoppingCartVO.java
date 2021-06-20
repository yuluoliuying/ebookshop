package com.example.orderservice.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCartVO {

    private String orderId;

    private Long bookId;

    private BigDecimal unitPrice;

    private Integer mount;

    private String payment;

    private String imageUrl;

    private String bookName;



}
