package com.example.server.controller;

import com.example.server.entity.BookInfo;
import com.example.server.entity.User;
import com.example.server.service.BookInfoService;
import com.example.server.service.OrdersService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

//    @Autowired
//    HttpServletRequest req;
    @Autowired
    UserService UserService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    BookInfoService bookInfoService;





}
