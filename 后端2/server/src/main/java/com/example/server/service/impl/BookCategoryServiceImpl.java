package com.example.server.service.impl;

import com.example.server.entity.BookCategory;
import com.example.server.mapper.BookCategoryMapper;
import com.example.server.service.BookCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyhui
 * @since 2021-06-17
 */
@Service
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory> implements BookCategoryService {

}
