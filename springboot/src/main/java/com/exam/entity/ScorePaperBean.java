package com.exam.entity;

/**
 * 用于页面展示分支和试卷
 */
public class ScorePaperBean {
    private Integer paperId;
    private Integer userId;
    private String paperTitle;
    private Integer score;
    // YYYYMMDDHHMMSS
    private String answerTime;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }
}
