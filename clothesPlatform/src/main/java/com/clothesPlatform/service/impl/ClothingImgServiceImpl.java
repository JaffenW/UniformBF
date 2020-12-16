package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.ClothingImg;
import com.clothesPlatform.repository.ClothingImgRepository;
import com.clothesPlatform.repository.ClothingRepository;
import com.clothesPlatform.service.ClothingImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClothingImgServiceImpl implements ClothingImgService {
    @Autowired
    ClothingImgRepository clothingImgRepository;
    @Override
    public boolean saveClothingImg(ClothingImg clothingImg) {
        clothingImgRepository.save(clothingImg);
        return true;
    }

    @Override
    public String findImgByClothingId(int clothingId) {
        return clothingImgRepository.findImgByClothingId(clothingId);
    }
}
