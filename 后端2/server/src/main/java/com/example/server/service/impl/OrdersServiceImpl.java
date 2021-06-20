package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.entity.Orders;
import com.example.server.entity.User;
import com.example.server.mapper.OrdersMapper;
import com.example.server.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyhui
 * @since 2021-06-17
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Override
    public Map<String,Object> getByUserId(Integer user_id) {
//        getMap()
        return getMap(new QueryWrapper<Orders>().eq("user_id",user_id));
    }
}
