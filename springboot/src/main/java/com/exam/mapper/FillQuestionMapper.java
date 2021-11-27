package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

//填空题
@Mapper
public interface FillQuestionMapper {

    @Options(useGeneratedKeys = true, keyProperty = "questionId")
    @Insert("insert into fill_question(question,answer,analysis,score,level,typeId) values " +
            "(#{question},#{answer},#{analysis},#{score},#{level},#{typeId})")
    int add(FillQuestion fillQuestion);

    @Delete("delete from fill_question where questionId = #{questionId}")
    int deleteById(int questionId);

    @Update("update fill_question set question = #{question}, answer = #{answer},analysis = #{analysis}," +
            "score = #{score},level = #{level},typeId = #{typeId} where questionId = #{questionId}")
    int update(FillQuestion question);

    @Select("select * from fill_question")
    List<FillQuestion> findAll();

    @Select("select * from fill_question where questionId = #{questionId}")
    List<FillQuestion> findById(Integer questionId);

    /**
     * 查询最后一条questionId
     */
    @Select("select questionId from fill_question order by questionId desc limit 1")
    FillQuestion findOnlyQuestionId();

    /**
     *  根据页码获取问题列表
     */
    @Select("select * from fill_question order by questionId")
    IPage<FillQuestion> findByPage(Page<?> page);

    /**
     *  根据提别和页码获取问题列表
     */
    @Select("select * from fill_question where typeId = #{typeId} order by questionId")
    IPage<FillQuestion> findByTypeAndPage(Page<?> page,String typeId);

    @Select("select * from fill_question where questionId in (select questionId from paper_question where questionType = 1 and paperId = #{paperId}) order by questionId")
    List<FillQuestion> findByIdAndType(Integer paperId);

    @Select({"<script> select * from fill_question where typeId in " +
            "<foreach item = 'item' index = 'index' collection = 'ids' open = '(' separator = ',' close = ')' >" +
            "#{item} " +
            "</foreach></script>"})
    List<FillQuestion> findBySubject(@Param("ids") List<Integer> typeIds);


}
