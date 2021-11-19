package com.exam.mapper;

import com.exam.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select userName,sex,email,cardId,role from user")
    public List<User> findAll();

    @Select("select userName,sex,email,cardId,role from user where adminId = #{userId}")
    public User findById(Integer userId);

    @Delete("delete from user where userId = #{userId}")
    public int deleteById(int userId);

    @Update("update user set userName = #{userName},sex = #{sex}," +
            "email = #{email},pwd = #{pwd},cardId = #{cardId},role = #{role} where userId = #{userId}")
    public int update(User user);

    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("insert into user(userName,sex,email,pwd,cardId,role) " +
            "values(#{userName},#{sex},#{email},#{pwd},#{cardId},#{role})")
    public int add(User user);

    @Select("select userName,sex,email,cardId,role from user where role = #{roleId}")
    public List<User> findAllByRole(String roleId);
}
