package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.Post;
import com.clothesPlatform.repository.PostRepository;
import com.clothesPlatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    //查询所有的帖子
    @Override
    public Page<Post> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return postRepository.findAll(pageable);
    }

    //根据id查询某一个帖子
    @Override
    public Post findPostById(Integer postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public List<Post> findPostByNtse(String name, String title, @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return postRepository.findPostByNtse("%"+name+"%", "%"+title+"%", start, end);
    }

    //添加一个帖子
    @Override
    public String addPost(Post post) {
        Post post1 = postRepository.save(post);
        return "200";
    }

    //根据id删除一个帖子
    @Override
    public boolean deletePostById(Integer postId) {
        postRepository.deleteById(postId);
        if (!postRepository.findById(postId).isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
