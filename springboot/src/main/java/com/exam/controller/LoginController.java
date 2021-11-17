package com.exam.controller;

import com.exam.entity.*;
import com.exam.serviceimpl.LoginServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody Login login) {

        Integer username = login.getUsername();
        String password = login.getPassword();
        User userRes = loginService.userLogin(username, password);
        if (userRes != null) {
            return ApiResultHandler.buildApiResult(200, "请求成功", userRes);
        }

        return ApiResultHandler.buildApiResult(400, "请求失败", null);
    }
}
