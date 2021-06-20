package com.example.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.orderservice.entity.*;
import com.example.orderservice.mapper.OrderDetailMapper;
import com.example.orderservice.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Override
    public List<ShoppingCartVO> getShoppingCartInfo(List<OrderPriceVo> orderIdList) {
        List<ShoppingCartVO> returnList = new ArrayList<>();
        for (OrderPriceVo vo : orderIdList) {
            String orderId = vo.getOrderId();
            String payment = vo.getPayment();
            QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
            wrapper.eq("order_id", orderId);
            OrderDetail one = baseMapper.selectOne(wrapper);
            ShoppingCartVO cartVO = new ShoppingCartVO();
            cartVO.setBookId(one.getBookId());
            cartVO.setBookName(one.getBookName());
            cartVO.setImageUrl(one.getImageUrl());
            cartVO.setMount(one.getMount());
            cartVO.setUnitPrice(one.getUnitPrice());
            cartVO.setOrderId(one.getOrderId());
            cartVO.setPayment(payment);
            returnList.add(cartVO);
        }
        return returnList;
    }

    @Override
    public void deleteFromOrderDetail(String order_id, Long book_id) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", order_id);
        wrapper.eq("book_id", book_id);
        baseMapper.delete(wrapper);
    }

    @Override
    public void addShoppingCart(OrderDetail orderDetail) {
        Date date = new Date();
        orderDetail.setDeliveryTime(date);
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderDetail.getOrderId());
        OrderDetail one = baseMapper.selectOne(wrapper);
        if (one != null) {
            Integer mount = one.getMount() + orderDetail.getMount();
            orderDetail.setMount(mount);
            orderDetail.setOrderDetailId(one.getOrderDetailId());
            baseMapper.updateById(orderDetail);
        } else {
            baseMapper.insert(orderDetail);
        }
    }

    @Override
    public void updateOrder(UpdateDetailVO order) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", order.getOrderId());
        OrderDetail select = baseMapper.selectOne(wrapper);
        if (!select.getMount().equals(order.getOrderMount())) {
            select.setMount(order.getOrderMount());
            baseMapper.updateById(select);
        }

    }

    @Override
    public OrderDetail getOrderList(String orderId) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderId);
        OrderDetail one = baseMapper.selectOne(wrapper);
        return one;
    }

    @Override
    public List<OrderDetail> getStoreOrderList(Integer store_id) {
        QueryWrapper<OrderDetail>wrapper = new QueryWrapper<>();
        wrapper.eq("store_id",store_id);
        wrapper.ne("receive_status",1);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void postOrder(String order_id) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",order_id);
        OrderDetail one = baseMapper.selectOne(wrapper);
        one.setPostStatus("1");
        Date date = new Date();
        one.setDeliveryTime(date);
        baseMapper.updateById(one);
    }

    @Override
    public void confirmOrder(String order_id, Integer rate) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",order_id);
        OrderDetail one = baseMapper.selectOne(wrapper);
        one.setReceiveStatus("1");
        one.setScore(rate+"");
    }

    @Override
    public OrderDetail getStoreOrderInfo(String order_id) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",order_id);
        OrderDetail detail = baseMapper.selectOne(wrapper);
        return detail;
    }
}
