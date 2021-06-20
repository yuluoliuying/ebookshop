package com.example.server.service.impl;

import com.example.server.entity.BookDesc;
import com.example.server.mapper.BookDescMapper;
import com.example.server.service.BookDescService;
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
public class BookDescServiceImpl extends ServiceImpl<BookDescMapper, BookDesc> implements BookDescService {

}
