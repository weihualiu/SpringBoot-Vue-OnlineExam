package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import com.exam.entity.ScorePaperBean;

import java.util.List;

public interface ScoreService {
    int add(Score score);

    List<Score> findAll();

    IPage<ScorePaperBean> findByUserId(Page page, Integer userId);

    List<Score> findById(Integer userId);

    IPage<Score> findByPaperId(Page page, Integer paperId);

    List<Score> findByPaperId(Integer paperId);

}
