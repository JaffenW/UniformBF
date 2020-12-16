package com.clothesPlatform.controller;

import com.clothesPlatform.entity.User;
import com.clothesPlatform.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录密码校验，传入的参数有userId，pssword，校验成功返回200，失败抛出RuntimeException异常")
    @PostMapping("/login")
    public String checkUser(@RequestParam("userId") String userId,@RequestParam("password") String password){
        if (userService.checkPassword(userId,password)){
            System.out.println("登录成功");
            return "200";
        }else {
            System.out.println("登录失败");
            throw new RuntimeException("账号或密码错误");
        }
    }

    @ApiOperation("注册一个新账号，传入userId，password，创建成功返回200，失败返回null")
    @PostMapping("/register")
    public String userRegister(@RequestParam("userId") String userid,@RequestParam("password") String password){
        User user = new User();
        Date date = new Date();
        user.setUserId(userid);
        user.setPassword(password);
        user.setRole("normal");
        user.setRegisterDate(date);
        if (userService.saveUser(user)){
            return "200";
        }else{
            return null;
        }

    }

    @ApiOperation("修改用户个人信息等操作")
    @PostMapping("/modifyUserInfo")
    public String modifyUserInfo(@RequestParam("userName") String userName,@RequestParam("height") int height,@RequestParam("weight") int weight,@RequestParam("gender") String gender,@RequestParam("password") String password,@RequestParam("role") String role,@RequestParam("userId") String userId,@RequestParam("address") String address,@RequestParam("phone") String phone) {
        if (userService.modifyUserInfo(userName,height,weight,gender,password,role,userId,address,phone)) {
            return "200";
        }else {
            return null;
        }
    }

    @ApiOperation("根据userId查询某一个用户，成功返回一个user对象，失败返回null")
    @GetMapping("/findUser/{userId}")
    @ResponseBody
    public User findUser(@PathVariable("userId")String userId){
        User user = userService.findUser(userId);
        if (user != null){
            System.out.println(user.toString());
            return user;
        }else {
            return null;
        }
    }

    @ApiOperation("分页查询所有用户，需传入page和size两个参数")
    @GetMapping("/findAllUser/{page}/{size}")
    public Page<User> findAllUser(@PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return userService.findAllUser(page,size);
    }

    @ApiOperation("通过用户id，用户姓名模糊查询用户，需传入userId,username两个参数")
    @PostMapping("/searchUsers")
    public List<User> searchUsers(@RequestBody User user) {
        System.out.println("userid:"+user.getUserId());
        return userService.findUserByIn(user.getUserId(),user.getUserName());
    }

    @ApiOperation("通过用户id删除用户，传入参数是userId")
    @DeleteMapping("/deleteUserByUserId")
    public void deleteUserByUserId(String userId) {
        userService.deleteUserByUserId(userId);
    }


}
