package com.example.bookservice.entity;

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
@ApiModel(value="BookDesc对象", description="")
public class BookDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "book_id", type = IdType.AUTO)
    private Long bookId;

    private String bookDesc;

    @TableField(fill = FieldFill.INSERT)
    private Date created;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;


}
