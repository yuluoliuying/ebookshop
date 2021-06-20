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
public class BookCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cate_id", type = IdType.AUTO)
    private Integer cateId;

    private Integer parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;


}
