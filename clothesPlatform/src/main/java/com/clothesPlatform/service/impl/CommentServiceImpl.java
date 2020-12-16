package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.Comment;
import com.clothesPlatform.repository.CommentRepository;
import com.clothesPlatform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findAllByPostId(Integer postId) {
        return commentRepository.findByPostIdOrderByStoreyDesc(postId);
    }

    @Override
    public void deleteCommentsByPostId(Integer postId) {
        commentRepository.deleteAllByPostId(postId);
    }

    @Override
    public boolean deleteCommentByCommentId(Integer commentId) {
        commentRepository.deleteById(commentId);
        Optional<Comment> op = commentRepository.findById(commentId);
        if (op.isPresent()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void addComment(Comment comment) {
        Comment cm = commentRepository.save(comment);
        System.out.println(cm.toString());
    }


}
