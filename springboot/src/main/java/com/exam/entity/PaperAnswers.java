package com.exam.entity;

import java.util.List;

/**
 * 试卷上传答题类
 */
public class PaperAnswers {
    private Integer userId;
    private Integer paperId;
    private List<PaperAnswer> answerList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public List<PaperAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<PaperAnswer> answerList) {
        this.answerList = answerList;
    }
}
