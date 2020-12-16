package com.clothesPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data//自动添加getter和setter方法
@AllArgsConstructor//自动添加全参的构造函数
@NoArgsConstructor//自动添加无参的构造函数
@Table(name = "[user]")
public class User {//用户类
    @Id
    @Column(name = "user_id")
    private String userId;//账号
    @Column(name = "password")
    private String password;//密码
    @Column(name = "role")
    private String role;//职位
    @Column(name = "user_name")
    private String userName;//用户名
    @Column(name = "gender")
    private String gender;//性别
    @Column(name = "height")
    private int height;//身高
    @Column(name = "weight")
    private int weight;//体重
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "registerDate")
    private Date registerDate;//用户注册时间
    @Column(name = "address")
    private String address;//用户地址
    @Column(name = "phone")
    private String phone;//用户手机号

}
