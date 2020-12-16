package com.clothesPlatform.service;

import com.clothesPlatform.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAllByPostId(Integer postId);

    void deleteCommentsByPostId(Integer postId);

    boolean deleteCommentByCommentId(Integer commentId);

    void addComment(Comment comment);

}
