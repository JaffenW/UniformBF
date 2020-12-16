package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.Collection;
import com.clothesPlatform.entity.ShoppingCart;
import com.clothesPlatform.repository.CollectionRepository;
import com.clothesPlatform.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionRepository collectionRepository;
    @Override
    public String saveCollection(Collection collection) {
        Collection collection1 = collectionRepository.save(collection);
        if(collection1 != null) {
            return "200";
        }else {
            return "error";
        }
    }

    @Override
    public List<Collection> findCollectionByUserId(String uId) {
        return collectionRepository.findCollectionByUserId(uId);
    }

    @Override
    public void deleteCollectionsByUserId(String uId) {
        collectionRepository.deleteCollectionsByUserId(uId);
    }

    @Override
    public void deleteCollectionByCollectionId(Integer collectionId) {
        collectionRepository.deleteById(collectionId);
    }
}
