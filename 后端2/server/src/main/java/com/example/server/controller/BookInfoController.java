package com.example.server.controller;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.server.common.lang.Result;
import com.example.server.entity.BookInfo;
import com.example.server.entity.Orders;
import com.example.server.entity.User;
import com.example.server.service.BookCategoryService;
import com.example.server.service.BookInfoService;
import com.example.server.service.OrdersService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.Param;

import java.security.Principal;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyhui
 * @since 2021-06-17
 */
@RestController
@RequestMapping("/")
public class BookInfoController extends BaseController {
    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private OrdersService ordersService;


    public  Result home(Principal principal){
        User user= UserService.getById(principal.getName());

        return Result.succ("");
    }

    //根据图书ID显示图书的详情
    @GetMapping("/bookinfo/{book_id}")
    public Result bookinfo(@PathVariable Long book_id){
        BookInfo info = bookInfoService.getById(book_id);
        return Result.succ(info);
    }

    //根据显示用户的购物车
//    @GetMapping("/orders/{user_id}")
//    public Result bookinfo(@PathVariable Integer user_id){
//        Orders orders = ordersService.getById(user_id);
//        return Result.succ(orders);
//    }

//    @GetMapping("/orders/{order_id}")
//    public Result bookinfo(@PathVariable String order_id){
//        Orders orders = ordersService.getById(order_id);
//        return Result.succ(orders);
//    }
    @GetMapping("/orders/{user_id}")
    public Result bookinfo(@PathVariable Integer user_id){
        Map<String,Object> m = ordersService.getByUserId(user_id);
        return Result.succ(m);
    }


    @GetMapping("/home")
    public Result books(){
        return Result.succ(bookInfoService.list());
    }


}
