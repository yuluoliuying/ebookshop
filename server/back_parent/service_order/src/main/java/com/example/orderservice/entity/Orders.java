package com.example.orderservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Orders对象", description="")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "order_id", type = IdType.ID_WORKER_STR)
    private String orderId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分")
    private String payment;

    @ApiModelProperty(value = "支付类型，1、微信，2、支付宝")
    private Integer paymentType;

    @ApiModelProperty(value = "状态：0、未付款，1、已付款，2、未发货，3、已发货，4、交易成功，5、交易关闭")
    private Integer status;

    @ApiModelProperty(value = "邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分")
    private String postFee;

    @ApiModelProperty(value = "订单创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "订单更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer orderMount;

    @ApiModelProperty(value = "付款时间")
    private Date paymentTime;

    @ApiModelProperty(value = "交易完成时间")
    private Date endTime;

    @ApiModelProperty(value = "交易关闭时间")
    private Date closeTime;

    @ApiModelProperty(value = "物流名称")
    private String shippingName;

    @ApiModelProperty(value = "物流单号")
    private String shippingCode;

    @ApiModelProperty(value = "买家留言")
    private String buyerMessage;

    @ApiModelProperty(value = "买家是否已经评价")
    private Integer buyerRate;


}
