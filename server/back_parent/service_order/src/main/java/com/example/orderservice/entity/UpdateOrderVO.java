package com.example.orderservice.entity;

import lombok.Data;

import java.util.List;

@Data
public class UpdateOrderVO {

    private List<UpdateDetailVO> orderList;



}
