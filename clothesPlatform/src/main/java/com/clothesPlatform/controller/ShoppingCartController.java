package com.clothesPlatform.controller;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.ShoppingCart;
import com.clothesPlatform.repository.ShoppingCartRepository;
import com.clothesPlatform.service.ClothingService;
import com.clothesPlatform.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
@CrossOrigin
public class ShoppingCartController {

    @Autowired
    public ShoppingCartService shoppingCartService;
    @Autowired
    public ClothingService clothingService;
    @Autowired
    public ShoppingCartRepository shoppingCartRepository;

    @ApiOperation("添加购物车")
    @PostMapping("/saveCart")
    public String saveCart(@RequestParam("uId") String uId,@RequestParam("cId") int cId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCId(cId);
        shoppingCart.setUId(uId);
        String cart = shoppingCartService.saveCart(shoppingCart);
        if(cart != null) {
            return "200";
        }else {
            return "error";
        }
    }

    @ApiOperation("查询单个用户的购物车，传入参数是uId")
    @GetMapping("/findCartByUserId/{uId}")
    public List<ShoppingCart> findCartByUserId(@PathVariable("uId") String uId) {
        List<ShoppingCart> list1 = shoppingCartService.findCartByUserId(uId);
        List<ShoppingCart> list2 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            ShoppingCart shoppingCart = list1.get(i);
            Clothing clothing = clothingService.findClothing(shoppingCart.getCId());
            shoppingCart.setClothing(clothing);
            list2.add(shoppingCart);
        }
        return list2;
    }

    @ApiOperation("删除与某个用户相关的购物车(清空购物车)，传入参数是uId")
    @PostMapping("/deleteCartsByUserId")
    public void deleteCartsByUserId(@RequestParam("uId") String uId) {
        shoppingCartService.deleteCartsByUserId(uId);
    }

    @ApiOperation("删除购物车内一件商品，传入参数是cartId")
    @PostMapping("/deleteCartByCartId")
    public void deleteCartByCartId(@RequestParam("cartId") int cartId) {
        shoppingCartService.deteleCartByCartId(cartId);
    }

    @ApiOperation("展示某一用户购物车内所有商品总价格，传入参数是uId")
    @GetMapping("/totalPrice/{uId}")
    public int totalPrice(@PathVariable("uId") String uId) {
        return shoppingCartService.totalPrice(uId);
    }
}
