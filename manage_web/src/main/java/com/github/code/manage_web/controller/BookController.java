package com.github.code.manage_web.controller;

import com.github.code.manage_web.dao.CommentDao;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Resource
    private CommentDao commentDao;

    @GetMapping
    public String getById() {
        commentDao.selectById(1);
        System.out.println(commentDao.selectById(1));
        return "xxxxx";
    }


}
