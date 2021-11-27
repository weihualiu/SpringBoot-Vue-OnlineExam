package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.PageBean;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @PostMapping("/judgeQuestion/add")
    public ApiResult add(@RequestBody JudgeQuestion JudgeQuestion) {
        int res = judgeQuestionService.add(JudgeQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    @PostMapping("/judgeQuestion/deleteById")
    public ApiResult deleteById(@RequestBody JudgeQuestion question) {
        System.out.println("根据ID删除用户");
        judgeQuestionService.deleteById(question.getQuestionId());
        return ApiResultHandler.success();
    }

    @PostMapping("/judgeQuestion/update")
    public ApiResult update(@RequestBody JudgeQuestion question) {
        System.out.println("更新用户信息");
        return ApiResultHandler.success(judgeQuestionService.update(question));
    }

    @PostMapping("/judgeQuestion/findAll")
    public ApiResult findAll() {
        System.out.println("查询全部用户");
        return ApiResultHandler.success(judgeQuestionService.findAll());
    }

    @PostMapping("/judgeQuestion/findById")
    public ApiResult findById(@RequestBody JudgeQuestion question) {
        System.out.println("根据ID查找用户"+question.getQuestionId());
        return ApiResultHandler.success(judgeQuestionService.findById(question.getQuestionId()));
    }

    @PostMapping("/judgeQuestion/JudgeQuestionId")
    public ApiResult findOnlyQuestionId() {
        JudgeQuestion res = judgeQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @PostMapping("/judgeQuestion/findByPage")
    public ApiResult findByPage(PageBean page) {
        Page<JudgeQuestion> questionPage = new Page<>(page.getPageNo(),page.getSize());
        IPage<JudgeQuestion> res = judgeQuestionService.findByPage(questionPage);
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @PostMapping("/judgeQuestion/findByTypeAndPage")
    public ApiResult findByTypeAndPage(PageBean page,String typeId) {
        Page<JudgeQuestion> questionPage = new Page<>(page.getPageNo(),page.getSize());
        IPage<JudgeQuestion> res = judgeQuestionService.findByTypeAndPage(questionPage,typeId);
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }


}
