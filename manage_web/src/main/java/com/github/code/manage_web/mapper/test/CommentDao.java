package com.github.code.manage_web.mapper.test;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.code.manage_web.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("crm")
public interface CommentDao extends BaseMapper<Comment> {


}
