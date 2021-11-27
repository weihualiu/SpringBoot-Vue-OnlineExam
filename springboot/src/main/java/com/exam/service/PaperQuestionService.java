package com.exam.service;

import com.exam.entity.PaperQuestion;

import java.util.List;

public interface PaperQuestionService {

    public int add(PaperQuestion paperQuestion);

    public int add(Integer paperId, List<String> list);
}
