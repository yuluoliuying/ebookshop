package com.example.server.mapper;

import com.example.server.common.lang.Result;
import com.example.server.entity.BookInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.entity.User;
import com.example.server.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wyhui
 * @since 2021-06-17
 */

public interface BookInfoMapper extends BaseMapper<BookInfo> {


}
