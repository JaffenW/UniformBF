package com.clothesPlatform.service;

import com.clothesPlatform.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Page<Post> findAll(int page, int size);

    Post findPostById(Integer postId);

    List<Post> findPostByNtse(String name, String title, @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date end);

    String addPost(Post post);

    boolean deletePostById(Integer postId);
}
