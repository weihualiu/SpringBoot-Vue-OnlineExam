package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.User;

import java.util.List;

public interface UserService {

    int add(User user);

    int update(User user);

    int deleteById(int userId);

    List<User> findAll();

    User findById(Integer userId);

    IPage<User> findByPage(Page page);
    /**
     * 根据角色获取用户列表
     */
    List<User> findALLByRole(String roleId);

    List<User> findUsersById(Integer paperId);
}
