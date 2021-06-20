package com.example.server.service;

import com.example.server.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyhui
 * @since 2021-06-17
 */
public interface OrdersService extends IService<Orders> {

    Map<String,Object> getByUserId(Integer user_id);
}
