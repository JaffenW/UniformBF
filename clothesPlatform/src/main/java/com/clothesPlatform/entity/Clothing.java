package com.clothesPlatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clothing {//服装类
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothes_id")
    private int clothesId;//服装id
    @Column(name = "type")
    private String type;//服装的类型
    @Column(name = "description")
    private String description;//服装的描述
    @Column(name = "rent")
    private int rent;//租金
    @Column(name = "belong")
    private String belong;//服装所属
    @Column(name = "clothing_img")
    private String clothingImg;//服装图片
}
