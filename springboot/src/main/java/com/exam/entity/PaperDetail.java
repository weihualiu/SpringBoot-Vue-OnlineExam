package com.exam.entity;


import java.util.List;
import java.util.Map;

/**
 * 试卷详情
 */
public class PaperDetail {
    // 试卷基本信息
    private PaperManage paperManage;
    // 授权的用户
    private List<User> users;
    // 分配的题
    private Map<String, List<?>> questions;

    public PaperManage getPaperManage() {
        return paperManage;
    }

    public void setPaperManage(PaperManage paperManage) {
        this.paperManage = paperManage;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<String, List<?>> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, List<?>> questions) {
        this.questions = questions;
    }
}
