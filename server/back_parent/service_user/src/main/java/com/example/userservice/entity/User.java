package com.example.userservice.entity;

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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String nickname;

    private String password;

    private String gender;

    private String email;

    private String phone;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    private String location;

    private Integer age;

    @ApiModelProperty(value = "国家")
    private String country;

    private String detailAddress;

    @ApiModelProperty(value = "身份")
    private String identity;

    private String active;

    @ApiModelProperty(value = "用户激活码")
    private String code;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;

    @TableField(fill = FieldFill.INSERT)
    private Date created;


}
