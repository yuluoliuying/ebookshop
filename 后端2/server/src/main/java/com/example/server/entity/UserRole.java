package com.example.server.entity;


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
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;


}
