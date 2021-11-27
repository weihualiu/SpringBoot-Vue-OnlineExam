package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;

import java.util.List;

public interface FillQuestionService {

    int add(FillQuestion fillQuestion);

    int deleteById(int questionId);

    int update(FillQuestion question);

    List<FillQuestion> findAll();

    List<FillQuestion> findById(Integer paperId);

    FillQuestion findOnlyQuestionId();

    IPage<FillQuestion> findByPage(Page<?> page);

    IPage<FillQuestion> findByTypeAndPage(Page<?> page,String subject);

    List<FillQuestion> findByIdAndType(Integer paperId);

    List<FillQuestion> findBySubject(List<Integer> typeIds);
}
