//package com.github.code.manage_web.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * 数据源配置
// */
//@Configuration
//public class DataSourceConfig {
//    @Primary
//    @Bean(name = "db_crm")
//    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.db-crm")
//    public DataSource dBCrm() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "db_cert")
//    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.db-cert")
//    public DataSource dBSrcUser() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "db_test")
//    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.test")
//    public DataSource dBTest() {
//        return DataSourceBuilder.create().build();
//    }
//}
//
