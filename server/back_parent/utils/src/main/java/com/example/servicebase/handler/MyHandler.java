package com.example.servicebase.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常处理
 */

@Data
@AllArgsConstructor //自动生成有参数的构造方法
@NoArgsConstructor  //生成五参数构造方法
public class MyHandler extends RuntimeException {

    private String msg;     //异常信息
    private Integer code;   //状态码

    public MyHandler(int i, String msg) {
        this.code = i;
        this.msg = msg;
    }
}
