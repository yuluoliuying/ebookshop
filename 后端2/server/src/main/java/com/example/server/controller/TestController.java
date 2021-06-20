package com.example.server.controller;

import com.example.server.common.lang.Result;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    UserService UserService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test")
    public Result test() {

        return Result.succ(UserService.list());
    }
//    @GetMapping("/test")
//    public Object test() {
//
//        return UserService.list();
//    }
@GetMapping("/test/pass")
public Result pass() {
    String password = bCryptPasswordEncoder.encode("1234");
    boolean matchs = bCryptPasswordEncoder.matches("1234", password);
    System.out.println("匹配结果" + matchs);
    return Result.succ(password);
}
}
