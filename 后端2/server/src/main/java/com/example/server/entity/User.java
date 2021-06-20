package com.example.server.entity;

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
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String nickname;

    private String password;

    private String gender;

    private String email;

    private String phone;

    /**
     * 邮政编码
     */
    private String zipCode;

    private String location;

    private Integer age;

    /**
     * 国家
     */
    private String country;

    private String detailAddress;

    /**
     * 身份
     */
    private String identity;

    private String active;

    /**
     * 用户激活码
     */
    private String code;


    public Integer getId() {
        return this.userId;
    }
}
