package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PageBean;
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
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public ApiResult add(@RequestBody User user) {
        System.out.println("添加用户");
        return ApiResultHandler.success(userService.add(user));
    }

    @PostMapping("/user/deleteById")
    public ApiResult deleteById(@RequestBody User user) {
        System.out.println("根据ID删除用户");
        userService.deleteById(user.getUserId());
        return ApiResultHandler.success();
    }

    @PostMapping("/user/update")
    public ApiResult update(@RequestBody User user) {
        System.out.println("更新用户信息");
        return ApiResultHandler.success(userService.update(user));
    }

    @PostMapping("/users/findAll")
    public ApiResult findAll() {
        System.out.println("查询全部用户");
        return ApiResultHandler.success(userService.findAll());
    }

    @PostMapping("/user/findById")
    public ApiResult findById(@RequestBody User user) {
        System.out.println("根据ID查找用户"+user.getUserId());
        return ApiResultHandler.success(userService.findById(user.getUserId()));
    }

    @PostMapping("/user/findByPage")
    public ApiResult findByPage(@RequestBody PageBean page){
        System.out.println("分页查找用户"+page.getPageNo());
        Page<User> userPage = new Page<>(page.getPageNo(),page.getSize());
        IPage<User> res = userService.findByPage(userPage);
        return ApiResultHandler.buildApiResult(200,"分页查询结果",res);
    }

    @PostMapping("/users/findAllByRole")
    public ApiResult findAllByRole(String roleId) {
        return ApiResultHandler.success(userService.findALLByRole(roleId));
    }
}
