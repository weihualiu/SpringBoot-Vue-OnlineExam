package com.exam.entity;

import lombok.Data;

//填空题实体类
@Data
public class FillQuestion {
    private Integer questionId;  //问题id

    private String question;    //试题内容

    private String answer;      //正确答案

    private String analysis;    //题目解析

    private Integer score;    //份数

    private String level;     //难度等级

    private Integer typeId;    //所属题目类型

}
