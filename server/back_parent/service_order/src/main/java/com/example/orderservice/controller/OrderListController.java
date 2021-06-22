package com.example.orderservice.controller;


import com.example.commonutils.R;
import com.example.commonutils.WriteDat;
import com.example.orderservice.client.BookClient;
import com.example.orderservice.client.UserClient;
import com.example.orderservice.entity.*;
import com.example.orderservice.service.OrderDetailService;
import com.example.orderservice.service.OrderShippingService;
import com.example.orderservice.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping("/orderservice/orderinfo")
public class OrderListController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private BookClient bookClient;

    @Autowired
    private OrderShippingService orderShippingService;

    @Autowired
    private UserClient userClient;

    // 查询订单列表
    @GetMapping("getOrderList/{user_id}")
    public R getOrderList(@PathVariable Integer user_id){
        List<Orders> ordersList =  ordersService.getOrderList(user_id);
        List<OrderListVO> listVOS = new ArrayList<>();
        for(Orders orders:ordersList){
            OrderListVO vo = new OrderListVO();
            OrderDetail detail = orderDetailService.getOrderList(orders.getOrderId());
            vo.setBookName(detail.getBookName());
            vo.setDeliveryTime(detail.getDeliveryTime());
            vo.setImageUrl(detail.getImageUrl());
            vo.setMount(detail.getMount());
            vo.setOrderId(detail.getOrderId());
            vo.setPayment(orders.getPayment());
            vo.setStatus(orders.getStatus());
            vo.setStoreId(detail.getStoreId());
            vo.setUnitPrice(detail.getUnitPrice());
            vo.setBookId(detail.getBookId());
            listVOS.add(vo);
        }
        return R.ok().data("orderList", listVOS);
    }

    // 用户点击支付修改订单状态
    @PostMapping("payOrder")
    public R payOrder(@RequestBody UpdateOrderVO updateOrderVO){
//        List<String> orderList = updateOrderVO.getOrderList();
//        ordersService.payOrder(orderList);
        List<UpdateDetailVO> orderList = updateOrderVO.getOrderList();
        for(UpdateDetailVO order:orderList){
            Long book_id = order.getBookId();
            Long userId = order.getUserId();
            R r = bookClient.updateBookStore(book_id,userId);
            if (r.getCode()==20000){
                String content = userId+","+book_id+","+"8";
                log.info("写入文件的content：    "+content);
                WriteDat writeDat = new WriteDat();
                writeDat.writeDat(content);
                ordersService.updateOrder(order);
                orderDetailService.updateOrder(order);
            }
        }
        return R.ok();
    }

    // 商家查询店铺订单列表
    @GetMapping("getStoreOrderList/{store_id}")
    public R getStoreOrderList(@PathVariable Integer store_id){
        List<OrderListVO> orderListVOS = new ArrayList<>();
        List<OrderDetail> orderDetails =  orderDetailService.getStoreOrderList(store_id);
        for(OrderDetail detail:orderDetails){
            Orders order = ordersService.getById(detail.getOrderId());
            OrderListVO vo = new OrderListVO();
            vo.setBookName(detail.getBookName());
            vo.setDeliveryTime(detail.getDeliveryTime());
            vo.setImageUrl(detail.getImageUrl());
            vo.setMount(detail.getMount());
            vo.setOrderId(detail.getOrderId());
            vo.setPayment(order.getPayment());
            vo.setStatus(order.getStatus());
            vo.setStoreId(detail.getStoreId());
            vo.setUnitPrice(detail.getUnitPrice());
            vo.setBookId(detail.getBookId());
            orderListVOS.add(vo);
        }
        return R.ok().data("storeOrderList",orderListVOS);
    }

    // 商家查询订单详情
    @GetMapping("getStoreOrderInfo/{order_id}")
    public R getStoreOrderInfo(@PathVariable String order_id){
        Orders order = ordersService.getById(order_id);
        OrderDetail detail = orderDetailService.getStoreOrderInfo(order_id);
        System.out.println(detail);
        StoreOrderInfoVO storeOrderInfoVO = new StoreOrderInfoVO();
        storeOrderInfoVO.setBookId(detail.getBookId());
        storeOrderInfoVO.setBookName(detail.getBookName());
        storeOrderInfoVO.setImageUrl(detail.getImageUrl());
        storeOrderInfoVO.setMount(detail.getMount());
        storeOrderInfoVO.setOrderId(order.getOrderId());
        storeOrderInfoVO.setPayment(order.getPayment());
        storeOrderInfoVO.setUnitPrice(detail.getUnitPrice());
        storeOrderInfoVO.setUserId(order.getUserId());
        Map<String, Object> map = userClient.getUserInfoById(order.getUserId()).getData();
        storeOrderInfoVO.setDetailAddress((String) map.get("address"));
        storeOrderInfoVO.setPhone((String) map.get("phone"));
        storeOrderInfoVO.setUsername((String) map.get("name"));
        return R.ok().data("storeOrderInfo",storeOrderInfoVO);
    }

    // 商家对订单执行发货操作
    @PostMapping("businessPostOrder")
    public R businessPostOrder(@RequestBody OrderShipping shipping){
        ordersService.postOrder(shipping.getOrderId());
        orderDetailService.postOrder(shipping.getOrderId());
        orderShippingService.postOrder(shipping);
        return R.ok();
    }

    // 用户点击确认收到货物 确认订单
    @GetMapping("confirmOrder/{order_id}/{rate}")
    public R confirmOrder(@PathVariable String order_id, @PathVariable Integer rate){
        ordersService.confirmOrder(order_id, rate);
        orderDetailService.confirmOrder(order_id, rate);
        return R.ok();
    }


}
