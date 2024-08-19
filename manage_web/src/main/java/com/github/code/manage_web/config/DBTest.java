//package com.github.code.manage_web.config;
//
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
///**
// * 数据库leadnews_article
// */
//@Configuration
//@MapperScan(basePackages = {"com.github.code.manage_web.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryTest")
//public class DBTest {
//    @Bean(name="sqlSessionFactoryTest")
//    public SqlSessionFactory sqlSessionFactoryTest(@Qualifier("db_test") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath:**/*.xml"));
//        return sqlSessionFactory.getObject();
//    }
//
//    @Bean(name="sqlSessionTemplateTest")
//    public SqlSessionTemplate sqlSessionTemplateTest(@Qualifier("sqlSessionFactoryTest") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
//
