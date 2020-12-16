package com.clothesPlatform.repository;

import com.clothesPlatform.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query
    List<Comment> findByPostIdOrderByStoreyDesc(Integer postId);

//    删除某个帖子下的所有评论
    @Transactional
    @Modifying
    @Query(value = "delete from comment where pid =?1",nativeQuery = true)
    void deleteAllByPostId(Integer postId);

//    查询某个帖子下评论的最大楼层
    @Query(value = "select ifnull(max(storey),0) from comment where pid =?1",nativeQuery = true)
    int findStoreyByPostId(Integer postId);
}
