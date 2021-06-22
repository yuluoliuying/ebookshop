package com.example.bookservice.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IndexBookVO {

    private Long bookId;

    private String name;

    private String press;

    private String author;

    private BigDecimal price;

    private BigDecimal marketPrice;

    private String imageUrl;

}
