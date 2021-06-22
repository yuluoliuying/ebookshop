package com.example.bookservice.entity;

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
@ApiModel(value="BookInfo对象", description="")
public class BookInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "book_id", type = IdType.AUTO)
    private Long bookId;

    private Integer bookCategoryId;

    private Integer storeId;

    private String name;

    @ApiModelProperty(value = "简介")
    private String outline;

    @ApiModelProperty(value = "商品详情")
    private String detail;

    @ApiModelProperty(value = "出版社")
    private String press;

    private String publishDate;

    private String size;

    private String version;

    private String author;

    @ApiModelProperty(value = "翻译者")
    private String translator;

    private String isbn;

    private BigDecimal price;

    @ApiModelProperty(value = "总页数")
    private Integer pages;

    @ApiModelProperty(value = "目录")
    private String catalog;

    @ApiModelProperty(value = "市场价 定价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "会员价格")
    private BigDecimal memberPrice;

    @ApiModelProperty(value = "成交量")
    private Integer dealMount;

    @ApiModelProperty(value = "浏览量")
    private Integer lookMount;

    private BigDecimal discount;

    @ApiModelProperty(value = "版面图片")
    private String imageUrl;

    @ApiModelProperty(value = "库存数量")
    private Integer storeMount;

    @ApiModelProperty(value = "入库时间")
    private Date storeTime;

    @ApiModelProperty(value = "封装方式")
    private String packStyle;

    @ApiModelProperty(value = "是否上架")
    private Integer isShelf;

    @ApiModelProperty(value = "类别名称")
    private String cname;

    private String description;

    private String cata;

    private String content;


}
