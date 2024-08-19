package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.code.manage_web.mapper.test.CommentDao;
import com.github.code.manage_web.domain.Comment;
import com.github.code.manage_web.service.ICommentService;
import org.springframework.stereotype.Service;

@Service
public class ICommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements ICommentService {
}
