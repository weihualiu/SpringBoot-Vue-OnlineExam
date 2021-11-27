package com.exam.service;

import com.exam.entity.User;

public interface LoginService {

    public User userLogin(String username, String password);
}
