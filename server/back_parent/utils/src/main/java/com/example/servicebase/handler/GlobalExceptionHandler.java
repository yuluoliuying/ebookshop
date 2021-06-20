package com.example.servicebase.handler;


import com.example.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j  //将异常信息写入日志文件
public class GlobalExceptionHandler {

    //指定出现什么异常之后执行这个方法
    @ExceptionHandler(Exception.class)  //所有异常
    @ResponseBody   //有异常的时候通过ResponseBody可以返回数据
    public R error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("执行了全局异常处理。。。");
    }

    @ExceptionHandler(ArithmeticException.class)  //特定异常
    @ResponseBody   //有异常的时候通过ResponseBody可以返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了Arithmetic异常处理。。。");
    }
}
