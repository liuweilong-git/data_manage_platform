package com.github.code.manage_web.service.impl;

import com.github.code.manage_web.domain.manage.Comment;
import com.github.code.manage_web.mapper.manage.CommentMapper;
import com.github.code.manage_web.service.manage.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
