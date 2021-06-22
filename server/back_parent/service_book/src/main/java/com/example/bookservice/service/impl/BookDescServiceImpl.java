package com.example.bookservice.service.impl;

import com.example.bookservice.entity.BookDesc;
import com.example.bookservice.mapper.BookDescMapper;
import com.example.bookservice.service.BookDescService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class BookDescServiceImpl extends ServiceImpl<BookDescMapper, BookDesc> implements BookDescService {

}
