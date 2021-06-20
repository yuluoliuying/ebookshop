package com.example.userservice.service;

import com.example.commonutils.R;
import com.example.userservice.entity.LoginVO;
import com.example.userservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;


public interface UserService extends IService<User> {

    R login(LoginVO loginVO);

    R register(User user);

    User select(String token);

    boolean updateUser(User user);

    R alipayLogin(String userId, String gender);

    void logout(String token);
}
