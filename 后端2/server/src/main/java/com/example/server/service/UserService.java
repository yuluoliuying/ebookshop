package com.example.server.service;

import com.example.server.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyhui
 * @since 2021-06-17
 */
public interface UserService extends IService<User> {

    User getByUsername(String username);
}
