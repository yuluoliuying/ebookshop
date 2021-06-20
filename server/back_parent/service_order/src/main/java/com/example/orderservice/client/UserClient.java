package com.example.orderservice.client;


import com.example.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-user")
public interface UserClient {
    @GetMapping("/userservice/user/getUserInfoById/{user_id}")
    public R getUserInfoById(@PathVariable("user_id") Integer user_id);
}
