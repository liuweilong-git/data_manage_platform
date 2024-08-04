package com.github.code.manage_ut;

import com.github.code.manage_web.dao.CommentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ManageUtApplication.class)
class ManageUtApplicationTests {

    @Autowired
    private CommentDao commentDao;

    @Test
    void contextLoads() {
        //调用要测对象的对应的方法
        commentDao.selectById(1);
        System.out.println(commentDao.selectById(1));
    }

}