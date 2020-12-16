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
public class Post {//帖子表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;//帖子id
    @Column(name = "[uid]")
    private String uid;//发帖人id
    @Column(name = "username")
    private String username;//发帖人姓名
    @Column(name = "title")
    private String title;//帖子标题
    @Column(name = "content")
    private String content;//帖子内容
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "[date]")
    private Date date;//发帖日期
}
