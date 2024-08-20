package com.github.code.manage_web;
import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot3.autoconfigure.properties.DruidStatProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) 没有数据库时需要添加才能成功
@SpringBootApplication
//@MapperScan(basePackages = "com.github.code.manage_web.mapper")
//@EnableJpaRepositories(repositoryImplementationPostfix = "Impl")
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication(exclude = {
//        DruidDataSourceAutoConfigure.class,DataSourceAutoConfiguration.class,
////        HibernateJpaConfiguration.class
//})
//
//@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
//@MapperScan(basePackages = {"com.github.code.manage_web.mapper.test"}, sqlSessionFactoryRef = "sqlSessionFactoryTest")
//@MapperScan(basePackages = {"com.rain.mapper.user"}, sqlSessionFactoryRef = "sqlSessionFactoryUser")
@EnableScheduling
public class ManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageWebApplication.class, args);
    }

}
