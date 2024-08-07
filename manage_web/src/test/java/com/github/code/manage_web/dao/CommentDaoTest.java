package com.github.code.manage_web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.code.manage_web.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest()
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    void contextLoads() {
        //调用要测对象的对应的方法
        commentDao.selectById(1);
        System.out.println(commentDao.selectById(1));
    }

    @Test
    void testSava() {
        Comment comment = new Comment();
        comment.setContent("test 测试数据2");
        commentDao.insert(comment);
    }

    @Test
    void testGetPage() {
        IPage<Comment> page = new Page<>(2,2);
         commentDao.selectPage(page,null);
//        System.out.println();
    }

    @Test
    void testGetBy() {
//        根据条件查询
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("content","test");
        commentDao.selectList(queryWrapper);
    }

    @Test
    void testGetBy2() {
//        根据条件查询
        String name = "test";
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(name!=null,Comment::getContent,name);
//        name 不为空才进行后续查询
        commentDao.selectList(queryWrapper);
    }



}
