package com.example.orderservice.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderDetail对象", description="")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_detail_id", type = IdType.AUTO)
    private Integer orderDetailId;

    @ApiModelProperty(value = "订单号")
    private String orderNumber;

    private String orderId;

    private Long bookId;

    private Integer storeId;

    @ApiModelProperty(value = "卖出数量")
    private Integer mount;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    @ApiModelProperty(value = "支付、发货状态")
    private String postStatus;

    @ApiModelProperty(value = "修订时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "收货状态")
    private String receiveStatus;

    private String imageUrl;

    private String bookName;

    @ApiModelProperty(value = "评分")
    private String score;


}
