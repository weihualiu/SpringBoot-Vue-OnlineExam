package com.exam.mapper;

import com.exam.entity.PaperUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperUserMapper {

    @Insert({"<script>" +
            "insert into paper_user(userId, paperId) values " +
            "<foreach collection = 'lists' item = 'item' index = 'index' open = '(' separator = '),(' close = ')' >" +
            "#{item.userId}, #{item.paperId} " +
            "</foreach>" +
            " </script>"})
    int insert(@Param(value = "lists") List<PaperUser> list);

    @Select("select count(0) from paper_user where paperId = #{paperId} and userId = #{userId}")
    Integer findPaper(@Param("paperId") Integer paperId, @Param("userId") Integer userId);
}
