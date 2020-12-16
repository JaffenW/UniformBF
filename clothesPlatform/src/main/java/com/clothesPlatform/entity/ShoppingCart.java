package com.clothesPlatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {//购物车类

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;//购物车id
    @Column(name = "u_id")
    private String uId;//购物车所属用户id
    @Column(name = "c_id")
    private int cId;//购物车内的服装id
    @Transient
    private Clothing clothing;
}
