package com.github.code.manage_web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.code.manage_web.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao extends BaseMapper<Comment> {


}
