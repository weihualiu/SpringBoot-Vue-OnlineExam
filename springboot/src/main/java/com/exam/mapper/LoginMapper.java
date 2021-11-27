package com.exam.mapper;

import com.exam.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select userId,loginName,userName,gender,email,role from user where loginName = #{username} and pwd = #{password}")
    public User userLogin(@Param("username") String username,@Param("password") String password);
}
