package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;

import java.util.List;

public interface MultiQuestionService {

    int add(MultiQuestion question);

    int deleteById(int questionId);

    int update(MultiQuestion question);

    List<MultiQuestion> findAll();

    List<MultiQuestion> findById(Integer questionId);

    MultiQuestion findOnlyQuestionId();

    IPage<MultiQuestion> findByPage(Page<?> page);

    IPage<MultiQuestion> findByTypeAndPage(Page<?> page,String subject);

    List<MultiQuestion> findByIdAndType(Integer paperId);

    List<MultiQuestion> findBySubject(List<Integer> typeIds);
}
