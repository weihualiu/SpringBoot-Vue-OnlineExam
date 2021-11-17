package com.exam.controller;

import com.exam.entity.User;
import com.exam.entity.ApiResult;
import com.exam.serviceimpl.UserServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ApiResult findAll(){
        System.out.println("查询全部");
        return ApiResultHandler.success(userService.findAll());
    }

    @GetMapping("/user/{userId}")
    public ApiResult findById(@PathVariable("userId") Integer userId){
        System.out.println("根据ID查找");
        return ApiResultHandler.success(userService.findById(userId));
    }

    @DeleteMapping("/user/{userId}")
    public ApiResult deleteById(@PathVariable("userId") Integer userId){
        userService.deleteById(userId);
        return ApiResultHandler.success();
    }

    @PutMapping("/user/{userId}")
    public ApiResult update(@PathVariable("userId") Integer userId, User user){
        return ApiResultHandler.success(userService.update(user));
    }

    @PostMapping("/user")
    public ApiResult add(User user){
        return ApiResultHandler.success(userService.add(user));
    }

    @PostMapping("/users/{roleId}")
    public ApiResult findAllByRole(String roleId) {
        return ApiResultHandler.success(userService.findALLByRole(roleId));
    }
}
