package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PaperManage;
import com.exam.mapper.PaperMapper;
import com.exam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Override
    public IPage<PaperManage> findAll(Page page) {
        return paperMapper.findAll(page);
    }

    @Override
    public PaperManage findById(Integer paperId) {
        return paperMapper.findById(paperId);
    }

    @Override
    public int add(PaperManage paperManage) {
        return paperMapper.add(paperManage);
    }

    @Override
    public int update(PaperManage paperManage) {
        return paperMapper.update(paperManage);
    }

    @Override
    public IPage<PaperManage> findByUserId(Page page, Integer userId) {
        return paperMapper.findByUserId(page, userId);
    }


}
