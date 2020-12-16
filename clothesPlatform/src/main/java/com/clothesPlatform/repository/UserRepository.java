package com.clothesPlatform.repository;

import com.clothesPlatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    @Transactional
    @Modifying
    @Query(value = "update `user` set user_name=?1,height=?2,weight=?3,gender=?4,password=?5,role=?6,address=?8,phone=?9 where user_id=?7", nativeQuery = true)
    void modifyUserInfo(String userName,int height,int weight,String gender,String password,String role,String userId,String address,String phone);
    @Query(value = "select u.* from `user` u where u.user_id like?1 and u.user_name like?2",nativeQuery = true)
    List<User> findUserByIn(String userId,String userName);
    @Query(value = "select u.* from `user` u where u.user_name like?1",nativeQuery = true)
    List<User> findUserByUsername(String userName);
    @Query(value = "select u.* from `user` u where u.user_id like?1",nativeQuery = true)
    List<User> findUserByUserId(String userId);
}
