package com.clothesPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {//帖子的评论
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;//评论id
    @Column(name = "[pid]")
    private int postId;//关联的的帖子id
    @Column(name = "[uid]")
    private String userId;//评论人
    @Column(name = "content")
    private String content;//评论内容
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "[date]")
    private Date date;//评论时间
    @Column(name = "storey")
    private int storey;//评论的楼层顺序
    @Transient
    private User user;
}
