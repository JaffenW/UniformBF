package com.clothesPlatform.controller;

import com.clothesPlatform.entity.Comment;
import com.clothesPlatform.entity.User;
import com.clothesPlatform.repository.CommentRepository;
import com.clothesPlatform.service.CommentService;
import com.clothesPlatform.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/collection")
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    CommentRepository commentRepository;

    @ApiOperation("查询某一个帖子下的所有评论,返回一个评论的对象数组")
    @GetMapping("/findAllByPostId/{postId}")
    public List<Comment> findAllByPostId(@PathVariable("postId")int postId){
        List<Comment> list1 = commentService.findAllByPostId(postId);
        List<Comment> list2 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Comment comment = list1.get(i);
            User user = userService.findUser(comment.getUserId());
            comment.setUser(user);
            list2.add(comment);
        }
        return list2;
    }

    @ApiOperation("根据评论id删除某一条评论,成功返回200，失败返回error")
    @PostMapping("/deleteComment/{commentId}")
    public String deleteCommentByCommentId(@PathVariable("commentId")int commentId){
        if (commentService.deleteCommentByCommentId(commentId)){
            return "200";
        }else {
            return "error";
        }
    }

    @ApiOperation("添加一条评论，需要传入一个comment对象，成功返回200")
    @PostMapping("/addComment")
    public String addComment(@RequestParam("postId") int postId,@RequestParam("content") String content,@RequestParam("userId") String userId){
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setDate(new Date());
        comment.setStorey(commentRepository.findStoreyByPostId(comment.getPostId())+1);
        commentService.addComment(comment);
        return "200";
    }
}
