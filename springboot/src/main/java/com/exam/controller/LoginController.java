package com.exam.controller;

import com.exam.entity.*;
import com.exam.serviceimpl.LoginServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody Login login, HttpServletRequest request) {

        Integer username = login.getUsername();
        String password = login.getPassword();
        User userRes = loginService.userLogin(username, password);
        if (userRes != null) {
            HttpSession session = request.getSession();
            session.setAttribute(session.getId(), userRes);
            return ApiResultHandler.buildApiResult(200, "请求成功", userRes);
        }

        return ApiResultHandler.buildApiResult(400, "请求失败", null);
    }
}
