package com.example.bookservice.service.impl;

import com.example.bookservice.entity.BookCategory;
import com.example.bookservice.mapper.BookCategoryMapper;
import com.example.bookservice.service.BookCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory> implements BookCategoryService {

}
