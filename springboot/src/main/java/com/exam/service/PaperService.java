package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PaperManage;

public interface PaperService {

    IPage<PaperManage> findAll(Page page);

    PaperManage findById(Integer paperId);

    int add(PaperManage paperManage);

    int update(PaperManage paperManage);

    IPage<PaperManage> findByUserId(Page page, Integer userId);
}
