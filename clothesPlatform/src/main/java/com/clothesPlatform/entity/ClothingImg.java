package com.clothesPlatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClothingImg {

    @Id
    @Column(name = "clothing_id")
    private int clothingId;//服装id
    @Column(name = "img_url")
    private String imgUrl;//服装图片地址
    @Column(name = "img_type")
    private String imgType;//服装图片类型
}
