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
@ApiModel(value="OrderShipping对象", description="")
public class OrderShipping implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "order_id", type = IdType.AUTO)
    private String orderId;

    @ApiModelProperty(value = "收货人全名")
    private String receiverName;

    @ApiModelProperty(value = "固定电话")
    private String receiverPhone;

    @ApiModelProperty(value = "移动电话")
    private String receiverMobile;

    @ApiModelProperty(value = "省份")
    private String receiverState;

    @ApiModelProperty(value = "城市")
    private String receiverCity;

    @ApiModelProperty(value = "区/县")
    private String receiverDistrict;

    @ApiModelProperty(value = "收货地址，如：xx路xx号")
    private String receiverAddress;

    @ApiModelProperty(value = "邮政编码,如：310001")
    private String receiverZip;

    @TableField(fill = FieldFill.INSERT)
    private Date created;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;


}
