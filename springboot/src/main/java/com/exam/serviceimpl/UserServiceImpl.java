package com.exam.serviceimpl;

import com.exam.entity.User;
import com.exam.mapper.UserMapper;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    public int deleteById(int userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int add(User user) {
        return 0;
    }

    @Override
    public List<User> findALLByRole(String roleId) {
        return userMapper.findAllByRole(roleId);
    }
}
