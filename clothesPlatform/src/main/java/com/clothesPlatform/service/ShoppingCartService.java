package com.clothesPlatform.service;

import com.clothesPlatform.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    String saveCart(ShoppingCart shoppingCart);

    List<ShoppingCart> findCartByUserId(String uId);

    int totalPrice(String uId);

    void deleteCartsByUserId(String uId);

    void deteleCartByCartId(Integer cartId);
}
