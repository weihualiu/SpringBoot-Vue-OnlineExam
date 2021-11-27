package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.mapper.FillQuestionMapper;
import com.exam.service.FillQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillQuestionServiceImpl implements FillQuestionService {

    @Autowired
    private FillQuestionMapper fillQuestionMapper;

    @Override
    public int add(FillQuestion fillQuestion) {
        return fillQuestionMapper.add(fillQuestion);
    }

    @Override
    public int deleteById(int userId) {
        return fillQuestionMapper.deleteById(userId);
    }

    @Override
    public int update(FillQuestion question) {
        return fillQuestionMapper.update(question);
    }

    @Override
    public List<FillQuestion> findAll() {
        return fillQuestionMapper.findAll();
    }

    @Override
    public List<FillQuestion> findById(Integer paperId) {
        return fillQuestionMapper.findById(paperId);
    }

    @Override
    public FillQuestion findOnlyQuestionId() {
        return fillQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public IPage<FillQuestion> findByPage(Page<?> page) {
        return fillQuestionMapper.findByPage(page);
    }

    @Override
    public IPage<FillQuestion> findByTypeAndPage(Page<?> page,String typeId) {
        return fillQuestionMapper.findByTypeAndPage(page,typeId);
    }

    @Override
    public List<FillQuestion> findByIdAndType(Integer paperId) {
        return fillQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public List<FillQuestion> findBySubject(List<Integer> typeIds) {
        return fillQuestionMapper.findBySubject(typeIds);
    }
}
