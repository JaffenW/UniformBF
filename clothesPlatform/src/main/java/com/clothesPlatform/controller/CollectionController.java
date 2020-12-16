package com.clothesPlatform.controller;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.Collection;
import com.clothesPlatform.entity.ShoppingCart;
import com.clothesPlatform.repository.CollectionRepository;
import com.clothesPlatform.service.ClothingService;
import com.clothesPlatform.service.CollectionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collection")
@CrossOrigin
public class CollectionController {

    @Autowired
    private CollectionService collectionService;
    @Autowired
    private ClothingService clothingService;
    @Autowired
    private CollectionRepository collectionRepository;

    @ApiOperation("添加收藏")
    @PostMapping("/saveCollection")
    public String saveCollection(@RequestParam("uId") String uId,@RequestParam("cId") int cId) {
        Collection collection = new Collection();
        collection.setCId(cId);
        collection.setUId(uId);
        String cart = collectionService.saveCollection(collection);
        if(cart != null) {
            return "200";
        }else {
            return "error";
        }
    }

    @ApiOperation("查询单个用户的收藏，传入参数是uId")
    @GetMapping("/findCollectionByUserId/{uId}")
    public List<Collection> findCollectionByUserId(@PathVariable("uId") String uId) {
        List<Collection> list1 = collectionService.findCollectionByUserId(uId);
        List<Collection> list2 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Collection collection = list1.get(i);
            Clothing clothing = clothingService.findClothing(collection.getCId());
            collection.setClothing(clothing);
            list2.add(collection);
        }
        return list2;
    }

    @ApiOperation("删除与某个用户相关的收藏(清空收藏夹)，传入参数是uId")
    @DeleteMapping("/deleteCollectionsByUserId")
    public void deleteCollectionsByUserId(String uId) {
        collectionService.deleteCollectionsByUserId(uId);
    }

    @ApiOperation("删除收藏内的一件商品，传入参数是cartId")
    @DeleteMapping("/deleteCollectionByCartId")
    public void deleteCollectionByCartId(int collectionId) {
        collectionService.deleteCollectionByCollectionId(collectionId);
    }

}
