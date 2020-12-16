package com.clothesPlatform.service;

import com.clothesPlatform.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    Page<User> findAllUser(int page,int size);

    boolean checkPassword(String userId,String password);

    boolean saveUser(User user);

    boolean modifyUserInfo(String userName,int height,int weight,String gender,String password,String role,String userId,String address,String phone);

    User findUser(String userId);

    List<User> findUserByIn(String userId,String userName);

    void deleteUserByUserId(String userId);
}
