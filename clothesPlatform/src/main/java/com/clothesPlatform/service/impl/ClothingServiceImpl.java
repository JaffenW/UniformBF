package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.ShoppingCart;
import com.clothesPlatform.repository.ClothingRepository;
import com.clothesPlatform.repository.OrderRepository;
import com.clothesPlatform.repository.ShoppingCartRepository;
import com.clothesPlatform.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@Repository
public class ClothingServiceImpl implements ClothingService {
    @Autowired
    ClothingRepository clothingRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public Page<Clothing> findAll(int page,int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return clothingRepository.findAll(pageable);
    }

    @Override
    public boolean saveClothing(Clothing clothing) {
        clothingRepository.save(clothing);
        return true;
    }

    @Override
    public boolean deleteClothing(int clothesId) {
        clothingRepository.deleteById(clothesId);
        Optional<Clothing> op = clothingRepository.findById(clothesId);
        if (op.isPresent()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Clothing> findClothingByType(String type) {
        return clothingRepository.findByTypeLike("%"+type+"%");
    }

    @Override
    public List<Clothing> findClothesInCartByUserId(String uId) {
        return clothingRepository.findClothesIdByUserIdInCart(uId);
    }

    @Override
    public List<Clothing> findClothesInCollectionByUserId(String uId) {
        return clothingRepository.findClothesIdByUserIdInCollection(uId);
    }

    @Override
    public List<Clothing> findClothingByTypeAndBelong(String type, String belong) {
        return clothingRepository.findClothesByTypeOrBelong("%"+type+"%","%"+belong+"%");
    }

    @Override
    public Clothing findClothing(int clothesId) {
        return clothingRepository.findById(clothesId).get();
    }

}
