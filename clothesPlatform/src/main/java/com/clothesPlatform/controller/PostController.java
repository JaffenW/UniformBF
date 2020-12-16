package com.clothesPlatform.controller;

import com.clothesPlatform.entity.Post;
import com.clothesPlatform.repository.PostRepository;
import com.clothesPlatform.service.CommentService;
import com.clothesPlatform.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    PostRepository postRepository;

    @ApiOperation("分页查询所有的帖子")
    @GetMapping("/findAllPosts/{page}/{size}")
    public Page<Post> findAllPosts(@PathVariable("page") int page,@PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return postService.findAll(page, size);
    }

    @ApiOperation("根据帖子Id查询某一个帖子，成功返回一个对象，如果没有查到抛出RuntimeException异常")
    @GetMapping("/findPost/{postId}")
    public Post findPost(@PathVariable("postId")int postId){
        Post op = postService.findPostById(postId);
        if (op != null){
            return op;
        }else {
            System.out.println("帖子不存在");
            throw new RuntimeException("帖子不存在");
        }
    }

    @ApiOperation("通过发帖人，题目，时间段查询帖子，传入参数为name,title,start,end")
    @GetMapping("/searchPosts/{name}/{title}/{start}/{end}")
    public List<Post> searchPosts(@PathVariable("name") String name,@PathVariable("title") String title,@PathVariable("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,@PathVariable("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) throws ParseException {
        System.out.println(start);
        System.out.println(end);
        System.out.println(name);
        System.out.println(title);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(start);
        String endTime = sdf.format(end);
        Date start1 = sdf.parse(startTime);
        Date end1 = sdf.parse(endTime);
        return postService.findPostByNtse(name, title, start, end);
    }

    @ApiOperation("添加一个帖子，需要传入一个Post对象，其中帖子id和Date由后端自动生成，成功返回200")
    @PostMapping("/addPost")
    public String addPost(@RequestBody Post post){
        Date date = new Date();
        post.setDate(date);
        post.setUsername(postRepository.findUsernameByUserId(post.getUid()));
        String post1 = postService.addPost(post);
        return "200";
    }

    @ApiOperation("根据postId删除一个帖子，成功返回200，失败返回error")
    @DeleteMapping("/deletePost")
    public String deletePost(int postId){
        commentService.deleteCommentsByPostId(postId);
        if (postService.deletePostById(postId)){
            return "200";
        }else {
            return "error";
        }
    }
}
