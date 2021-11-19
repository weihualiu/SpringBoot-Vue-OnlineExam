package com.exam.service;

import com.exam.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(Integer userId);

    public int deleteById(int userId);

    public int update(User user);

    public int add(User user);

    /**
     * 根据角色获取用户列表
     *
     * @param roleId 角色类型
     * @return
     */
    public List<User> findALLByRole(String roleId);
}
