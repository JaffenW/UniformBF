package com.clothesPlatform.repository;

import com.clothesPlatform.entity.ClothingImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClothingImgRepository extends JpaRepository<ClothingImg,Integer> {
    @Query(value = "select img_url from clothing_img where clothing_id=?1",nativeQuery = true)
    String findImgByClothingId(int clothingId);
}
