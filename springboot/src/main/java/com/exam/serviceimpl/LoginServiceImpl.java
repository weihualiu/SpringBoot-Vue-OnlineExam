package com.exam.serviceimpl;

import com.exam.entity.User;
import com.exam.mapper.LoginMapper;
import com.exam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User userLogin(Integer username, String password) {
        return loginMapper.userLogin(username,password);
    }

}
