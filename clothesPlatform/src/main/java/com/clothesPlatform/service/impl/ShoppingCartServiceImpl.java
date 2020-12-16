package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.ShoppingCart;
import com.clothesPlatform.repository.ShoppingCartRepository;
import com.clothesPlatform.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;

@Repository
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Override
    public String saveCart(ShoppingCart shoppingCart) {
        ShoppingCart cart = shoppingCartRepository.save(shoppingCart);
        if(cart != null) {
            return "200";
        }else {
            return "error";
        }
    }

    @Override
    public List<ShoppingCart> findCartByUserId(String uId) {
        return shoppingCartRepository.findCartByUserId(uId);
    }

    @Override
    public int totalPrice(String uId) {
        int totalDeposit = shoppingCartRepository.countUid(uId)*50;
        if(shoppingCartRepository.countUid(uId)==0){
            return 0;
        }else {
            return shoppingCartRepository.findRentInCartByUserId(uId)+totalDeposit;
        }
    }

    @Override
    public void deleteCartsByUserId(String uId) {
        shoppingCartRepository.deleteCartsByUserId(uId);
    }

    @Override
    public void deteleCartByCartId(Integer cartId) {
        shoppingCartRepository.deleteById(cartId);
    }
}
