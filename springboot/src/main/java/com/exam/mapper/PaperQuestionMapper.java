package com.exam.mapper;

import com.exam.entity.PaperQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaperQuestionMapper {

    @Insert("insert into paper_question(paperId, questionType,questionId) " +
            "values(#{paperId}, #{questionType},#{questionId})")
    public int add(PaperQuestion paperQuestion);

    @Insert({"<script>" +
            "insert into paper_question(paperId, questionType, questionId) values " +
            "<foreach collection = 'lists' item = 'item' index = 'index' open = '(' separator = '),(' close = ')' >" +
            "#{item.paperId}, #{item.questionType}, #{item.questionId} " +
            "</foreach>" +
            " </script>"})
    int insert(@Param(value = "lists")List<PaperQuestion> lists);
}
