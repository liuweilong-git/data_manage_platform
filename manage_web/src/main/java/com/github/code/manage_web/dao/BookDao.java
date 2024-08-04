package com.github.code.manage_web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.code.manage_web.domain.Book;
import org.springframework.web.bind.annotation.Mapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//mybatis写法
//@Mapper
//public interface BookDao {
//    @Select("select * from table where id = #{id}")
//    public Book getBookById(Integer id);
//}

//mybatis-plus写法
@Mapper
public interface BookDao extends BaseMapper<Book> {

}
