package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import com.exam.entity.ScorePaperBean;
import com.exam.mapper.ScoreMapper;
import com.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public int add(Score score) {
        return scoreMapper.add(score);
    }

    @Override
    public List<Score> findAll() {
        return scoreMapper.findAll();
    }

    @Override
    public IPage<ScorePaperBean> findByUserId(Page page, Integer userId) {
        return scoreMapper.findByUserId(page, userId);
    }

    @Override
    public List<Score> findById(Integer studentId) {
        return scoreMapper.findById(studentId);
    }

    @Override
    public IPage<Score> findByPaperId(Page page, Integer paperId) {
        return scoreMapper.findByPaperId(page, paperId);
    }

    @Override
    public List<Score> findByPaperId(Integer paperId) {
        return scoreMapper.findByPaperId(paperId);
    }

}
