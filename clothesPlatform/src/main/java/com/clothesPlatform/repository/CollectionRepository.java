package com.clothesPlatform.repository;

import com.clothesPlatform.entity.Collection;
import com.clothesPlatform.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Integer> {

    @Query(value = "select * from collection where u_id =?1",nativeQuery = true)
    List<Collection> findCollectionByUserId(String uId);

    @Transactional
    @Modifying
    @Query(value = "delete from collection where u_id =?1",nativeQuery = true)
    void deleteCollectionsByUserId(String uId);
}
