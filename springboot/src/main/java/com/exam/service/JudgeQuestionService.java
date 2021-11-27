package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;

import java.util.List;

public interface JudgeQuestionService {

    int add(JudgeQuestion judgeQuestion);

    int deleteById(int questionId);

    int update(JudgeQuestion question);

    List<JudgeQuestion> findAll();

    List<JudgeQuestion> findById(Integer questionId);

    JudgeQuestion findOnlyQuestionId();

    IPage<JudgeQuestion> findByPage(Page<?> page);

    IPage<JudgeQuestion> findByTypeAndPage(Page<?> page,String subject);

    List<JudgeQuestion> findByIdAndType(Integer paperId);

    List<JudgeQuestion> findBySubject(List<Integer> typeIds);

}
