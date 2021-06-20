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
public class BookDesc extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long bookId;

    private String bookDesc;


}
