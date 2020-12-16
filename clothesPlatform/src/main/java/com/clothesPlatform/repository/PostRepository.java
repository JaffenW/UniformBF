package com.clothesPlatform.repository;

import com.clothesPlatform.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query(value = "select user_name from `user` where user_id =?1",nativeQuery = true)
    String findUsernameByUserId(String uid);
    @Query(value = "select p.* from post p where p.username like?1 and p.title like?2 and p.date between DATE_FORMAT(?3,'%Y-%m-%d') and DATE_FORMAT(?4,'%Y-%m-%d')",nativeQuery = true)
    List<Post> findPostByNtse(String name, String title, @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date end);
}
