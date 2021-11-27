package com.exam.serviceimpl;

import com.exam.entity.QuestionChapter;
import com.exam.mapper.QuestionChapterMapper;
import com.exam.service.QuestionChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionChapterServiceImpl implements QuestionChapterService {

    @Autowired
    private QuestionChapterMapper questionChapterMapper;

    @Override
    public int add(QuestionChapter chapter) {
        return questionChapterMapper.add(chapter);
    }

    @Override
    public int deleteById(Integer chapterId) {
        return questionChapterMapper.deleteById(chapterId);
    }

    @Override
    public int update(QuestionChapter chapter) {
        return questionChapterMapper.update(chapter);
    }

    @Override
    public List<QuestionChapter> findAll() {
        return questionChapterMapper.findAll();
    }

    @Override
    public QuestionChapter findById(Integer userId) {
        return questionChapterMapper.findById(userId);
    }


    @Override
    public List<QuestionChapter> findByParentId(Integer parentId) {
        return questionChapterMapper.findByParentId(parentId);
    }

    @Override
    public List<QuestionChapter> findLeafById(Integer id) {
        List<QuestionChapter> questionChapters = questionChapterMapper.findByParentId(id);
        List<QuestionChapter> result = new ArrayList<>();
        findLeaf(questionChapters, result);
        return result;
    }

    private void findLeaf(List<QuestionChapter> list, List<QuestionChapter> result) {
        for(QuestionChapter element : list) {
            List<QuestionChapter> temp = questionChapterMapper.findByParentId(element.getTypeId());
            if(temp != null && temp.size() != 0) {
                findLeaf(temp, result);
            }else{
                result.add(element);
            }
        }
    }
}
