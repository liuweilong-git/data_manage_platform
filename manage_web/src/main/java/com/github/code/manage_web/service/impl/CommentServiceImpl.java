package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.code.manage_web.dao.CommentDao;
import com.github.code.manage_web.domain.Comment;
import com.github.code.manage_web.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public Boolean addComment(Comment comment) {
        return commentDao.insert(comment) >0;
    }

    @Override
    public Boolean deleteComment(Integer id) {
        return commentDao.deleteById(id) >0;
    }

    @Override
    public Boolean updateComment(Comment comment) {
        return commentDao.updateById(comment) >0;
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentDao.selectById(id);

    }

    @Override
    public List<Comment> getCommentByArticleId() {
        return commentDao.selectList(null) ;
    }

    @Override
    public IPage<Comment> getCommentByPage(Integer pageNum, Integer pageSize) {
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        return commentDao.selectPage(page, null);
    }
}
