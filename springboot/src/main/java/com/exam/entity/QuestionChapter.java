package com.exam.entity;

public class QuestionChapter {

    private Integer typeId;     //题目类型
    private String qTypeName;
    private Integer parentId;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getqTypeName() {
        return qTypeName;
    }

    public void setqTypeName(String qTypeName) {
        this.qTypeName = qTypeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
