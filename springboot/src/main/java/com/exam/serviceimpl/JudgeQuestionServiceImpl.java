package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.mapper.JudgeQuestionMapper;
import com.exam.service.JudgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeQuestionServiceImpl implements JudgeQuestionService {

    @Autowired
    private JudgeQuestionMapper judgeQuestionMapper;

    @Override
    public int add(JudgeQuestion question) {
        return judgeQuestionMapper.add(question);
    }

    @Override
    public int deleteById(int userId) {
        return judgeQuestionMapper.deleteById(userId);
    }

    @Override
    public int update(JudgeQuestion question) {
        return judgeQuestionMapper.update(question);
    }

    @Override
    public List<JudgeQuestion> findAll() {
        return judgeQuestionMapper.findAll();
    }

    @Override
    public List<JudgeQuestion> findById(Integer paperId) {
        return judgeQuestionMapper.findById(paperId);
    }

    @Override
    public JudgeQuestion findOnlyQuestionId() {
        return judgeQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public IPage<JudgeQuestion> findByPage(Page<?> page) {
        return judgeQuestionMapper.findByPage(page);
    }

    @Override
    public IPage<JudgeQuestion> findByTypeAndPage(Page<?> page,String typeId) {
        return judgeQuestionMapper.findByTypeAndPage(page,typeId);
    }

    @Override
    public List<JudgeQuestion> findByIdAndType(Integer paperId) {
        return judgeQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public List<JudgeQuestion> findBySubject(List<Integer> typeIds) {
        return judgeQuestionMapper.findBySubject(typeIds);
    }
}
