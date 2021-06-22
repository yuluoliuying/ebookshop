package com.example.orderservice.service;

import com.example.orderservice.entity.OrderShipping;
import com.baomidou.mybatisplus.extension.service.IService;


public interface OrderShippingService extends IService<OrderShipping> {

    void postOrder(OrderShipping shipping);
}
