package com.exam.mapper;

import com.exam.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select userId,userName,sex,email,cardId,role from user where userId = #{username} and pwd = #{password}")
    public User userLogin(Integer username, String password);
}
