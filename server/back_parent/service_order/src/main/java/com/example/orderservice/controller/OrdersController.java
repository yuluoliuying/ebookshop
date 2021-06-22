package com.example.orderservice.controller;


import com.example.commonutils.R;
import com.example.orderservice.entity.*;
import com.example.orderservice.service.OrderDetailService;
import com.example.orderservice.service.OrdersService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.reflections.Reflections.log;


@RestController
@RequestMapping("/orderservice/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("addShoppingCart")
    @ApiModelProperty(value = "用户将商品加入购物车", notes = "用户将商品加入购物车")
    public R addShoppingCart(@RequestBody ShoppingCartRequestVo shoppingCartRequestVo){
        Long bookId = shoppingCartRequestVo.getBookId();
        Integer mount = shoppingCartRequestVo.getOrderMount();
        String payment = shoppingCartRequestVo.getPayment();
        Integer storeId = shoppingCartRequestVo.getStoreId();
        BigDecimal unitPrice = shoppingCartRequestVo.getUnitPrice();
        Integer userId = shoppingCartRequestVo.getUserId();
        String imageUrl = shoppingCartRequestVo.getImageUrl();
        String bookName = shoppingCartRequestVo.getBookName();

//        Orders orders = new Orders();
//        orders.setPayment(payment);
//        orders.setOrderMount(mount);
//        orders.setUserId(userId);

        String order_id = ordersService.addShoppingCart(userId, bookId, mount, payment, unitPrice);

        log.info("order_id:       "+order_id);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBookId(bookId);
        orderDetail.setOrderNumber(order_id);
        orderDetail.setMount(mount);
        orderDetail.setOrderId(order_id);
        orderDetail.setStoreId(storeId);
        orderDetail.setUnitPrice(unitPrice);
        orderDetail.setBookName(bookName);
        orderDetail.setImageUrl(imageUrl);
        orderDetail.setPostStatus("0");
        orderDetail.setReceiveStatus("0");
        orderDetailService.addShoppingCart(orderDetail);
        return R.ok();
    }

    @GetMapping("checkShoppingCart/{user_id}")
    @ApiModelProperty(value = "用户查看购物车", notes = "用户查看购物车")
    public R checkShoppingCart(@PathVariable String user_id){
        List<OrderPriceVo> orderPriceVosList = ordersService.getShoppingCartBookId(user_id);
        List<ShoppingCartVO> shoppingCartVOList = orderDetailService.getShoppingCartInfo(orderPriceVosList);
        return R.ok().data("shoppingCartInfo",shoppingCartVOList);
    }

    @DeleteMapping("deleteShoppingCartProduct/{order_id}/{book_id}")
    @ApiModelProperty(value = "用户删除购物车商品", notes = "用户删除购物车商品")
    public R deleteShoppingCartProduct(@PathVariable String order_id, @PathVariable Long book_id){

        ordersService.deleteFromOrder(order_id);
        orderDetailService.deleteFromOrderDetail(order_id, book_id);
        return R.ok();
    }

}

