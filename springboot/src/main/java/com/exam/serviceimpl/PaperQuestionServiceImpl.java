package com.exam.serviceimpl;

import com.exam.entity.PaperQuestion;
import com.exam.mapper.PaperQuestionMapper;
import com.exam.service.PaperQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperQuestionServiceImpl implements PaperQuestionService {

    @Autowired
    private PaperQuestionMapper paperQuestionMapper;

    @Override
    public int add(PaperQuestion paperQuestion) {
        return paperQuestionMapper.add(paperQuestion);
    }

    @Override
    public int add(Integer paperId, List<String> list) {
        List<PaperQuestion> paperQuestionList = new ArrayList<>();

        for(String element: list) {
            String[] arr = element.split("-");
            PaperQuestion paperQuestion = new PaperQuestion();
            paperQuestion.setPaperId(paperId);
            paperQuestion.setQuestionType(Integer.valueOf(arr[0]));
            paperQuestion.setQuestionId(Integer.valueOf(arr[1]));
            paperQuestionList.add(paperQuestion);
        }

        paperQuestionMapper.insert(paperQuestionList);

        return 0;
    }
}
