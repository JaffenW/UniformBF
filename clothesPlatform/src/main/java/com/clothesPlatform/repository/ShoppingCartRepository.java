package com.clothesPlatform.repository;

import com.clothesPlatform.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {

    @Query(value = "select * from shopping_cart where u_id =?1",nativeQuery = true)
    List<ShoppingCart> findCartByUserId(String uId);

    @Query(value = "select sum(m.rent) s from clothing m left join shopping_cart s on m.clothes_id=s.c_id where u_id =?1",nativeQuery = true)
    int findRentInCartByUserId(String uId);
    @Query(value = "select count(u_id) from shopping_cart where u_id=?1",nativeQuery = true)
    int countUid(String uId);

    @Transactional
    @Modifying
    @Query(value = "delete from shopping_cart where u_id =?1",nativeQuery = true)
    void deleteCartsByUserId(String uId);
}
