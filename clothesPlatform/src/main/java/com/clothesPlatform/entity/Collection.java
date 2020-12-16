package com.clothesPlatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {//收藏夹类

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private int collectionId;//收藏id
    @Column(name = "u_id")
    private String uId;//用户id
    @Column(name = "c_id")
    private int cId;//服装id
    @Transient
    private Clothing clothing;
}
