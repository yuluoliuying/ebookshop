package com.example.server.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

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
public class BookInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "book_id", type = IdType.AUTO)
    private Long bookId;

    private Integer bookCategoryId;

    private Integer storeId;

    private String name;

    /**
     * 简介
     */
    private String outline;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 出版社
     */
    private String press;

    private String publishDate;

    private String size;

    private String version;

    private String author;

    /**
     * 翻译者
     */
    private String translator;

    private String isbn;

    private BigDecimal price;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 目录
     */
    private String catalog;

    /**
     * 市场价\定价
     */
    private BigDecimal marketPrice;

    /**
     * 会员价格
     */
    private BigDecimal memberPrice;

    /**
     * 成交量
     */
    private Integer dealMount;

    /**
     * 浏览量
     */
    private Integer lookMount;

    private BigDecimal discount;

    /**
     * 版面图片
     */
    private String imageUrl;

    /**
     * 库存数量
     */
    private Integer storeMount;

    /**
     * 入库时间
     */
    private LocalDateTime storeTime;

    /**
     * 封装方式
     */
    private String packStyle;

    /**
     * 是否上架
     */
    private Integer isShelf;

    /**
     * 类别名称
     */
    private String cname;

    private String description;

    private String cata;

    private String content;


}
