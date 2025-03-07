package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperManage {
    private Integer paperId;

    private String description;

    private Integer totalTime;

    private Integer totalScore;

    private String startTime;

    private String endTime;

}