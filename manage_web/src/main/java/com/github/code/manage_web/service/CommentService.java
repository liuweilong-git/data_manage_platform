package com.github.code.manage_web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.code.manage_web.domain.Comment;

import java.util.List;

public interface CommentService {
    Boolean addComment(Comment comment);
    Boolean deleteComment(Integer commentId);
    Boolean updateComment(Comment comment);
    Comment getCommentById(Integer commentId);
    List<Comment> getCommentByArticleId();
    IPage<Comment> getCommentByPage(Integer pageNum, Integer pageSize);
}
