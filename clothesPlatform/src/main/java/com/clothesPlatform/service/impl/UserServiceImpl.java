package com.clothesPlatform.service.impl;

import com.clothesPlatform.entity.User;
import com.clothesPlatform.repository.UserRepository;
import com.clothesPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public Page<User> findAllUser(int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean checkPassword(String userId,String password) {
        Optional<User> op = userRepository.findById(userId);
        User user = op.get();
        if (user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean saveUser(User user) {
        Optional<User> op = userRepository.findById(user.getUserId());
        if (!op.isPresent()){
            userRepository.save(user);
            return true;
        } else{
            return false;
        }

    }

    @Override
    public boolean modifyUserInfo(String userName,int height,int weight,String gender,String password,String role,String userId,String address,String phone) {
        userRepository.modifyUserInfo(userName,height,weight,gender,password,role,userId,address,phone);
        return true;
    }

    @Override
    public User findUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        }else {
            return null;
        }

    }

    @Override
    public List<User> findUserByIn(String userId, String userName) {
        if(userId != "") {
            return userRepository.findUserByUserId("%"+userId+"%");
        }else if (userName !="") {
            return userRepository.findUserByUsername("%"+userName+"%");
        }else {
            return userRepository.findUserByIn("%"+userId+"%","%"+userName+"%");
        }
    }

    @Override
    public void deleteUserByUserId(String userId) {
        userRepository.deleteById(userId);
    }
}
