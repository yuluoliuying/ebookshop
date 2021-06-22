package com.example.orderservice.client;


import com.example.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-book")
public interface BookClient {

    @GetMapping("/bookservice/book/updateBookStore/{book_id}/{user_id}")
    public R updateBookStore(@PathVariable("book_id") Long book_id,@PathVariable("user_id") Long user_id);
}
