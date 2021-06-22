package com.example.orderservice.service;

import com.example.orderservice.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.orderservice.entity.OrderPriceVo;
import com.example.orderservice.entity.ShoppingCartVO;
import com.example.orderservice.entity.UpdateDetailVO;

import java.util.List;


public interface OrderDetailService extends IService<OrderDetail> {

    List<ShoppingCartVO> getShoppingCartInfo(List<OrderPriceVo> orderIdList);

    void deleteFromOrderDetail(String order_id, Long book_id);

    void addShoppingCart(OrderDetail orderDetail);

    void updateOrder(UpdateDetailVO orderList);

    OrderDetail getOrderList(String orderId);

    List<OrderDetail> getStoreOrderList(Integer store_id);

    void postOrder(String order_id);

    void confirmOrder(String order_id, Integer rate);

    OrderDetail getStoreOrderInfo(String order_id);
}
