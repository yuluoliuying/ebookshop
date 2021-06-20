package com.example.orderservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StoreOrderInfoVO {

    private String orderId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    private String payment;

    private Long bookId;

    private String bookName;

    private String imageUrl;

    private Integer mount;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    private String username;

    private String phone;

    private String detailAddress;

}
