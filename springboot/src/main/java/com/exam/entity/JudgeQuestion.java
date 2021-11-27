package com.exam.entity;

import lombok.Data;

//判断题实体类
@Data
public class JudgeQuestion {
    private Integer questionId;

    private String question;

    private String answer;

    private String analysis; //题目解析

    private Integer score;

    private Integer typeId;

    private String level;
}