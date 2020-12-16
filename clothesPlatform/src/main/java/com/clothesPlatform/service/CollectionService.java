package com.clothesPlatform.service;

import com.clothesPlatform.entity.Collection;

import java.util.List;

public interface CollectionService {

    String saveCollection(Collection collection);

    List<Collection> findCollectionByUserId(String uId);

    void deleteCollectionsByUserId(String uId);

    void deleteCollectionByCollectionId(Integer collectionId);
}
