package com.exam.service;

import com.exam.entity.QuestionChapter;

import java.util.List;

public interface QuestionChapterService {

    public int add(QuestionChapter chapter);

    public int deleteById(Integer chapterId);

    public int update(QuestionChapter chapter);

    public List<QuestionChapter> findAll();

    public QuestionChapter findById(Integer chapterId);

    public List<QuestionChapter> findByParentId(Integer parentId);

    /**
     * 查询指定编号下的所有叶子节点数据
     * @param id
     * @return
     */
    public List<QuestionChapter> findLeafById(Integer id);

}
