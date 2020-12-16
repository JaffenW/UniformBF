package com.clothesPlatform.repository;

import com.clothesPlatform.entity.Clothing;
import com.clothesPlatform.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Array;
import java.util.List;

public interface ClothingRepository extends JpaRepository<Clothing,Integer> {
    @Query
    List<Clothing> findByTypeLike(String type);

    @Query(value = "select m.* from clothing m left join shopping_cart s on m.clothes_id=s.c_id where u_id=?1",nativeQuery = true)
    List<Clothing> findClothesIdByUserIdInCart(String uId);

    @Query(value = "select m.* from clothing m left join collection c on m.clothes_id=c.c_id where u_id=?1",nativeQuery = true)
    List<Clothing> findClothesIdByUserIdInCollection(String uId);

    @Query(value = "select m.* from clothing m where type like?1 and belong like?2",nativeQuery = true)
    List<Clothing> findClothesByTypeOrBelong(String type,String belong);

    @Query(value = "select max(clothes_id) from `clothing`",nativeQuery = true)
    int findMaxClothesId();

}
