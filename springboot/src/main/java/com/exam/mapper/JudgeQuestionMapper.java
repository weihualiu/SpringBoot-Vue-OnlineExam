package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//判断题
@Mapper
public interface JudgeQuestionMapper {

    @Options(useGeneratedKeys = true, keyProperty = "questionId")
    @Insert("insert into judge_question(question,answer,analysis,score,level,typeId) values " +
            "(#{question},#{answer},#{analysis},#{score},#{level},#{typeId})")
    int add(JudgeQuestion fillQuestion);

    @Delete("delete from judge_question where questionId = #{questionId}")
    int deleteById(int questionId);

    @Update("update judge_question set question = #{question}, answer = #{answer},analysis = #{analysis}," +
            "score = #{score},level = #{level},typeId = #{typeId} where questionId = #{questionId}")
    int update(JudgeQuestion question);

    @Select("select * from judge_question")
    List<JudgeQuestion> findAll();

    @Select("select * from judge_question where questionId = #{questionId}")
    List<JudgeQuestion> findById(Integer questionId);

    /**
     * 查询最后一条questionId
     */
    @Select("select questionId from judge_question order by questionId desc limit 1")
    JudgeQuestion findOnlyQuestionId();

    /**
     *  根据页码获取问题列表
     */
    @Select("select * from judge_question order by questionId")
    IPage<JudgeQuestion> findByPage(Page<?> page);

    /**
     *  根据提别和页码获取问题列表
     */
    @Select("select * from judge_question where typeId = #{typeId} order by questionId")
    IPage<JudgeQuestion> findByTypeAndPage(Page<?> page,String typeId);

    @Select("select * from judge_question where questionId in (select questionId from paper_question where questionType = 3 and paperId = #{paperId}) order by questionId")
    List<JudgeQuestion> findByIdAndType(Integer paperId);

    @Select({"<script> select * from judge_question where typeId in " +
            "<foreach item = 'item' index = 'index' collection = 'ids' open = '(' separator = ',' close = ')' >" +
            "#{item} " +
            "</foreach></script>"})
    List<JudgeQuestion> findBySubject(@Param("ids") List<Integer> typeIds);
}
