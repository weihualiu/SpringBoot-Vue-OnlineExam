package com.exam.mapper;

import com.exam.entity.QuestionChapter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionChapterMapper {

    @Options(useGeneratedKeys = true, keyProperty = "typeId")
    @Insert("insert into question_type(qTypeName,parentId) " +
            "values(#{qTypeName}, #{parentId})")
    public int add(QuestionChapter chapter);

    @Delete("delete from question_type where typeId = #{typeId}")
    public int deleteById(Integer typeId);

    @Update("update question_type set qTypeName = #{qTypeName}" +
            " where typeId = #{typeId}")
    public int update(QuestionChapter chapter);

    @Select("select typeId,qTypeName,parentId from question_type")
    public List<QuestionChapter> findAll();

    @Select("select typeId,qTypeName,parentId from question_type where typeId = #{typeId}")
    public QuestionChapter findById(Integer typeId);

    @Select("select typeId,qTypeName,parentId from question_type where parentId = #{parentId}")
    public List<QuestionChapter> findByParentId(Integer parentId);


}
