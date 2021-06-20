package com.example.server.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyhui
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private String orderId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private String payment;

    /**
     * 支付类型，1、微信，2、支付宝
     */
    private Integer paymentType;

    /**
     * 状态：0、未付款，1、已付款，2、未发货，3、已发货，4、交易成功，5、交易关闭
     */
    private Integer status;

    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private String postFee;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 订单更新时间
     */
    private LocalDateTime updateTime;

    private Integer orderMount;

    /**
     * 付款时间
     */
    private LocalDateTime paymentTime;

    /**
     * 交易完成时间
     */
    private LocalDateTime endTime;

    /**
     * 交易关闭时间
     */
    private LocalDateTime closeTime;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 物流单号
     */
    private String shippingCode;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家是否已经评价
     */
    private Integer buyerRate;


}
