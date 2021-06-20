package com.example.server.service.impl;

import com.example.server.entity.BookInfo;
import com.example.server.mapper.BookInfoMapper;
import com.example.server.service.BookInfoService;
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
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {

}
