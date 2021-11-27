package com.exam.service;

import com.exam.entity.PaperUser;

import java.util.List;

public interface PaperUserService {

    public int insert(List<PaperUser> list);

    Integer findPaper(Integer paperId, Integer userId);
}
