package com.clothesPlatform.service;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.ShoppingCart;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClothingService {

    Page<Clothing> findAll(int page,int size);

    boolean saveClothing(Clothing clothing);

    boolean deleteClothing(int clothesId);

    List<Clothing> findClothingByType(String type);

    List<Clothing> findClothesInCartByUserId(String uId);

    List<Clothing> findClothesInCollectionByUserId(String uId);

    List<Clothing> findClothingByTypeAndBelong(String type,String belong);

    Clothing findClothing (int clothing);

}
