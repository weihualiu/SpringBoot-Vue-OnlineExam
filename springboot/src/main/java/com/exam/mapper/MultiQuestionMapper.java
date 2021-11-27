package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

//选择题
@Mapper
public interface MultiQuestionMapper {

    @Options(useGeneratedKeys = true, keyProperty = "questionId")
    @Insert("insert into multi_question(question,answerA,answerB,answerC,answerD,rightAnswer,analysis,score,typeId,level) values " +
            "(#{question},#{answer},#{analysis},#{score},#{level},#{typeId})")
    int add(MultiQuestion fillQuestion);

    @Delete("delete from multi_question where questionId = #{questionId}")
    int deleteById(int questionId);

    @Update("update multi_question set question = #{question}, answerA = #{answerA},answerB = #{answerB},answerC = #{answerC},answerD = #{answerD},analysis = #{analysis}," +
            "score = #{score},typeId = #{typeId},level = #{level} where questionId = #{questionId}")
    int update(MultiQuestion question);

    @Select("select * from multi_question")
    List<MultiQuestion> findAll();

    @Select("select * from multi_question where questionId = #{questionId}")
    List<MultiQuestion> findById(Integer questionId);
    /**
     * 查询最后一条记录的questionId
     */
    @Select("select questionId from multi_question order by questionId desc limit 1")
    MultiQuestion findOnlyQuestionId();

    /**
     *  根据页码获取问题列表
     */
    @Select("select * from multi_question order by questionId")
    IPage<MultiQuestion> findByPage(Page<?> page);

    /**
     *  根据提别和页码获取问题列表
     */
    @Select("select * from multi_question where typeId = #{typeId} order by questionId")
    IPage<MultiQuestion> findByTypeAndPage(Page<?> page,String typeId);

    /**
     * select * from multiquestions where questionId in (
     * select questionId from papermanage where questionType = 1 and paperId = 1001
     * )
     */
    @Select("select * from multi_question where questionId in (select questionId from paper_question where questionType = 2 and paperId = #{paperId})")
    List<MultiQuestion> findByIdAndType(Integer PaperId);


    @Select({"<script> select * from multi_question where typeId in " +
            "<foreach item = 'item' index = 'index' collection = 'ids' open = '(' separator = ',' close = ')' >" +
            "#{item} " +
            "</foreach></script>"})
    List<MultiQuestion> findBySubject(@Param("ids") List<Integer> typeIds);

}
