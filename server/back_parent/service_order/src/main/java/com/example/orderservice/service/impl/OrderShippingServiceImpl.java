package com.example.orderservice.service.impl;

import com.example.orderservice.entity.OrderShipping;
import com.example.orderservice.mapper.OrderShippingMapper;
import com.example.orderservice.service.OrderShippingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class OrderShippingServiceImpl extends ServiceImpl<OrderShippingMapper, OrderShipping> implements OrderShippingService {

    @Override
    public void postOrder(OrderShipping shipping) {
        baseMapper.insert(shipping);
    }
}
