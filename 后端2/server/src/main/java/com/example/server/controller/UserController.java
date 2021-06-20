package com.example.server.controller;


import cn.hutool.core.map.MapUtil;
import com.example.server.common.lang.Result;
import com.example.server.entity.BookInfo;
import com.example.server.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyhui
 * @since 2021-06-17
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 获取用户信息接口
     * @param principal
     * @return
     */
    @GetMapping("/userInfo")
    public Result userInfo(Principal principal) {

        User User = UserService.getByUsername(principal.getName());

        return Result.succ(MapUtil.builder()
                .put("id", User.getId())
                .put("username", User.getUsername())
                .map()
        );
    }

}
