package com.github.code.manage_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) 没有数据库时需要添加才能成功
@SpringBootApplication
public class ManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageWebApplication.class, args);
    }

}
