package com.example.orderservice.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderListVO {

    private String orderId;

    private Integer mount;

    private BigDecimal unitPrice;

    private Date deliveryTime;

    private String imageUrl;

    private String bookName;

    private String payment;

    private Integer status;

    private Integer storeId;

    private Long bookId;
}
