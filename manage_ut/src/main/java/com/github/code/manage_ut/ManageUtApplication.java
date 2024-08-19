package com.github.code.manage_ut;

import org.springframework.boot.SpringApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@ComponentScan(basePackages = {"com.github.code.manage_ut","com.github.code.manage_web"})
@MapperScan(basePackages = "com.github.code.manage_web.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class ManageUtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageUtApplication.class, args);
    }

}
