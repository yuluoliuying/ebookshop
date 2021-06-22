package com.example.orderservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCartRequestVo {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分")
    private String payment;

    private Integer orderMount;

    private Long bookId;

    private Integer storeId;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    private String imageUrl;

    private String bookName;


}
