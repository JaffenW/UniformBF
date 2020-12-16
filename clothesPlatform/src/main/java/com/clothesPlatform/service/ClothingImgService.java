package com.clothesPlatform.service;

import com.clothesPlatform.entity.ClothingImg;

public interface ClothingImgService {

    boolean saveClothingImg(ClothingImg clothingImg);

    String findImgByClothingId(int clothingId);
}
