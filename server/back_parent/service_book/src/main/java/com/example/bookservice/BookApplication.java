package com.example.bookservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.example.bookservice.mapper")
@ComponentScan(basePackages = {"com.example"})
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class,args);
    }
}
