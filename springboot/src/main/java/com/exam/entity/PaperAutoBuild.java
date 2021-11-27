package com.exam.entity;

/**
 * @author auhiewuil@gmai.com
 * 自动组卷实体类
 */
public class PaperAutoBuild {
    // 试卷名称
    private String description;
    // 题目数量
    private Integer questionNums;
    // 试卷生效时间
    private String startTime;
    // 试卷失效时间
    private String endTime;
    // 试卷答题时长（分钟）
    private Integer totalTime;
    // 题目章节
    private Integer section;

    private Integer[] users;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuestionNums() {
        return questionNums;
    }

    public void setQuestionNums(Integer questionNums) {
        this.questionNums = questionNums;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer[] getUsers() {
        return users;
    }

    public void setUsers(Integer[] users) {
        this.users = users;
    }
}
