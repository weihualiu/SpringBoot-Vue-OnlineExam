package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PaperManage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PaperMapper {
    @Select("select paperId, description,totalTime,totalScore,startTime,endTime from paper_manage order by paperId desc")
    IPage<PaperManage> findAll(Page<PaperManage> page);

    @Select("select paperId, description, totalTime, totalScore, startTime, endTime from paper_manage " +
            "where paperId in (select paperId from paper_user where userId = #{userId}) order by paperId desc")
    IPage<PaperManage> findByUserId(Page<PaperManage> page, @Param("userId") Integer userId);

    @Select("select paperId, description,totalTime,totalScore,startTime,endTime from paper_manage where paperId = #{paperId}")
    PaperManage findById(Integer paperId);

    @Options(useGeneratedKeys = true, keyProperty = "paperId")
    @Insert("insert into paper_manage(description,totalTime,totalScore,startTime,endTime) values " +
            "(#{description},#{totalTime},#{totalScore},#{startTime},#{endTime})")
    int add(PaperManage paperManage);

    @Update("update paper_manage set description = #{description},totalTime = #{totalTime}," +
            "totalScore = #{totalScore},startTime = #{startTime},endTime = #{endTime} where paperId = #{paperId}")
    public int update(PaperManage paperManage);
}
