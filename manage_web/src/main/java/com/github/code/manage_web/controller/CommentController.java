package com.github.code.manage_web.controller;

import com.github.code.manage_web.controller.utils.R;
import com.github.code.manage_web.domain.Comment;
import com.github.code.manage_web.service.ICommentService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @GetMapping()
    public R getComments() {
        return new R(true,commentService.list());
    }

    @PostMapping
    public R save(@RequestBody Comment comment) {
        return new R(commentService.save(comment));

    }

    @PutMapping
    public R update(@RequestBody Comment comment) {
        return new R(commentService.updateById(comment));

    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(commentService.removeById(id));

    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id) {
        return new R(true,commentService.getById(id));
    }


}
