package com.exam.serviceimpl;

import com.exam.entity.PaperUser;
import com.exam.mapper.PaperUserMapper;
import com.exam.service.PaperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperUserServiceImpl implements PaperUserService {

    @Autowired
    private PaperUserMapper paperUserMapper;

    @Override
    public int insert(List<PaperUser> list) {
        return paperUserMapper.insert(list);
    }

    @Override
    public Integer findPaper(Integer paperId, Integer userId) {
        return paperUserMapper.findPaper(paperId, userId);
    }


}
