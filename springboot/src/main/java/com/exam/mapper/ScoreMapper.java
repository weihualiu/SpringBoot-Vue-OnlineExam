package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import com.exam.entity.ScorePaperBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScoreMapper {
    /**
     * @param score 添加一条成绩记录
     * @return
     */
    @Insert("insert into score(userId, paperId,score,answerDate) values(#{userId},#{paperId},#{score},#{answerDate})")
    int add(Score score);

    @Select("select userId,paperId,score,answerDate from score order by userId desc")
    List<Score> findAll();

    // 分页
    @Select("select s.paperId, s.userId, pm.description as paperTitle, 100/s.score*pm.totalScore as score, s.answerDate as answerTime from score s, paper_manage pm " +
            "where s.paperId = pm.paperId and s.userId = #{userId} order by s.paperId, s.userId desc")
    IPage<ScorePaperBean> findByUserId(Page<?> page, @Param("userId") Integer userId);

    // 不分页
    @Select("select userId,paperId,score,answerDate from score where userId = #{userId}")
    List<Score> findById(Integer userId);

    @Select("select userId, paperId, score, answerDate from score where paperId = #{paperId} order by userId")
    IPage<Score> findByPaperId(Page<?> page, Integer paperId);

    // 不分页
    @Select("select userId,paperId,score,answerDate from score where paperId = #{paperId}")
    List<Score> findByPaperId(Integer paperId);

//    /**
//     * @return 查询每位学生的学科分数。 max其实是假的，为了迷惑老师，达到一次考试考生只参加了一次的效果
//     */
//    @Select("select max(etScore) as etScore from score where examCode = #{examCode} group by studentId")
//    List<Score> findByExamCode(Integer paperId);
}
