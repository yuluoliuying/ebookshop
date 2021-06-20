package com.example.orderservice.service;

import com.example.commonutils.R;
import com.example.orderservice.entity.OrderPriceVo;
import com.example.orderservice.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.orderservice.entity.UpdateDetailVO;

import java.math.BigDecimal;
import java.util.List;


public interface OrdersService extends IService<Orders> {

    String addShoppingCart(Orders orders);

    List<OrderPriceVo> getShoppingCartBookId(String user_id);

    void deleteFromOrder(String order_id);

    List<Orders> getOrderList(Integer user_id);

    String addShoppingCart(Integer userId, Long bookId, Integer mount, String payment, BigDecimal unitPrice);

    R updateOrder(UpdateDetailVO orderList);

    void postOrder(String order_id);

    void confirmOrder(String order_id,Integer rate);
}
