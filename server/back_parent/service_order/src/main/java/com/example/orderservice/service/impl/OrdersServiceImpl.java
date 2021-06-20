package com.example.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.R;
import com.example.orderservice.entity.OrderDetail;
import com.example.orderservice.entity.OrderPriceVo;
import com.example.orderservice.entity.Orders;
import com.example.orderservice.entity.UpdateDetailVO;
import com.example.orderservice.mapper.OrdersMapper;
import com.example.orderservice.service.OrderDetailService;
import com.example.orderservice.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.reflections.Reflections.log;


@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrderDetailService orderDetailService;


    @Override
    public String addShoppingCart(Orders orders) {
        Date date = new Date();
        orders.setCreateTime(date);
        orders.setUpdateTime(date);
        orders.setStatus(0);
        baseMapper.insert(orders);
        return orders.getOrderId();
    }

    @Override
    public List<OrderPriceVo> getShoppingCartBookId(String user_id) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user_id);
        wrapper.eq("status", 0);
        List<Orders> orders = baseMapper.selectList(wrapper);
        List<OrderPriceVo> list = new ArrayList<>();
        for (Orders orders1 : orders) {
            OrderPriceVo vo = new OrderPriceVo();
            vo.setOrderId(orders1.getOrderId());
            vo.setPayment(orders1.getPayment());
            list.add(vo);
        }
        return list;
    }

    @Override
    public void deleteFromOrder(String order_id) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", order_id);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<Orders> getOrderList(Integer user_id) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user_id);
        wrapper.ne("status", 0);
        wrapper.ne("status", 4);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public String addShoppingCart(Integer userId, Long bookId, Integer mount, String payment, BigDecimal unitPrice) {
        Orders o = new Orders();
        QueryWrapper<Orders> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id", userId);
        wrapper1.eq("status",0);
        List<Orders> orders = baseMapper.selectList(wrapper1);
        boolean flag = false;
        String id = null;
        for (Orders order : orders) {
            String orderId = order.getOrderId();
            QueryWrapper<OrderDetail> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("order_id", orderId);
            wrapper2.eq("book_id", bookId);
            OrderDetail one = orderDetailService.getOne(wrapper2);
            if (one != null) {
                flag = true;
                mount = one.getMount() + mount;
                id = one.getOrderId();
                payment = unitPrice.multiply(BigDecimal.valueOf(mount)) + "";
//                log.info("重复添加：  "+ payment);
                log.info("重复添加：  " + payment);
                break;
            }
        }
        Date date = new Date();
        o.setCreateTime(date);
        o.setUpdateTime(date);
        o.setStatus(0);
        o.setUserId(userId);
        o.setOrderMount(mount);
        o.setPostFee("0.00");
        o.setPayment(payment);
        // 如果flag为true  说明原来数据库中该用户已经将同样商品加入到了购物车，这时我们直接修改他的数量和总价
        if (flag) {
            o.setOrderId(id);
            baseMapper.updateById(o);
            log.info("修改购物车成功");

        } else {
            baseMapper.insert(o);
            id = o.getOrderId();
        }
        return id;
    }

    @Override
    public R updateOrder(UpdateDetailVO order) {
        Date date = new Date();
        Orders select = baseMapper.selectById(order.getOrderId());
        if (select.getOrderMount().equals(order.getOrderMount())) {
            select.setStatus(1);
            select.setBuyerMessage(order.getUserId().toString());
            select.setPaymentType(2);
            select.setPaymentTime(date);
            baseMapper.updateById(select);
        } else {
            Integer mount = order.getOrderMount();
            BigDecimal unitPrice = order.getUnitPrice();
            String payment = unitPrice.multiply(BigDecimal.valueOf(mount)) + "";
            select.setStatus(1);
            select.setPayment(payment);
            select.setOrderMount(mount);
            select.setBuyerMessage(order.getUserId().toString());
            select.setPaymentType(2);
            select.setPaymentTime(date);
            baseMapper.updateById(select);
        }

        return null;
    }

    @Override
    public void postOrder(String order_id) {
        Orders select = baseMapper.selectById(order_id);
        select.setStatus(2);
        select.setShippingName("圆通");
        baseMapper.updateById(select);
    }

    @Override
    public void confirmOrder(String order_id, Integer rate) {
        Orders select = baseMapper.selectById(order_id);
        select.setStatus(4);
        Date date = new Date();
        select.setUpdateTime(date);
        select.setCloseTime(date);
        select.setEndTime(date);
        select.setBuyerRate(rate);
        baseMapper.updateById(select);
    }
}
