package com.exam.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Score {
    private Integer userId;

    private Integer paperId;

    private Integer score;

    private String answerDate;
}