package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("insert into user(loginName, userName,gender,email,pwd,role) " +
            "values(#{loginName}, #{userName},#{gender},#{email},#{pwd},#{role})")
    int add(User user);

    @Delete("delete from user where userId = #{userId}")
    int deleteById(int userId);

    @Update("update user set loginName = #{loginName}, userName = #{userName},gender = #{gender}," +
            "email = #{email},pwd = #{pwd},role = #{role} where userId = #{userId}")
    int update(User user);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where userId = #{userId}")
    User findById(Integer userId);

    @Select("select * from user order by userId")
    IPage<User> findByPage(Page<?> page);

    @Select("select * from user where role = #{roleId}")
    List<User> findAllByRole(String roleId);

    @Select("select * from user u, paper_user p where u.userId = p.userId and p.paperId = #{paperId} order by u.userId")
    List<User> findUsersById(Integer paperId);
}
