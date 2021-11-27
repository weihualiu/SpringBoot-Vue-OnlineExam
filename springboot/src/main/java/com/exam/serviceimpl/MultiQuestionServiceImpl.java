package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.mapper.MultiQuestionMapper;
import com.exam.service.MultiQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiQuestionServiceImpl implements MultiQuestionService {

    @Autowired
    private MultiQuestionMapper multiQuestionMapper;

    @Override
    public int add(MultiQuestion question) {
        return multiQuestionMapper.add(question);
    }

    @Override
    public int deleteById(int userId) {
        return multiQuestionMapper.deleteById(userId);
    }

    @Override
    public int update(MultiQuestion question) {
        return multiQuestionMapper.update(question);
    }

    @Override
    public List<MultiQuestion> findAll() {
        return multiQuestionMapper.findAll();
    }

    @Override
    public List<MultiQuestion> findById(Integer paperId) {
        return multiQuestionMapper.findById(paperId);
    }

    @Override
    public MultiQuestion findOnlyQuestionId() {
        return multiQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public IPage<MultiQuestion> findByPage(Page<?> page) {
        return multiQuestionMapper.findByPage(page);
    }

    @Override
    public IPage<MultiQuestion> findByTypeAndPage(Page<?> page, String typeId) {
        return multiQuestionMapper.findByTypeAndPage(page, typeId);
    }

    @Override
    public List<MultiQuestion> findByIdAndType(Integer PaperId) {
        return multiQuestionMapper.findByIdAndType(PaperId);
    }

    @Override
    public List<MultiQuestion> findBySubject(List<Integer> typeIds) {
        return multiQuestionMapper.findBySubject(typeIds);
    }
}
