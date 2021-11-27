package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.MultiQuestion;
import com.exam.entity.PageBean;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiQuestionController {

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @PostMapping("/multiQuestion/add")
    public ApiResult add(@RequestBody MultiQuestion MultiQuestion) {
        int res = multiQuestionService.add(MultiQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    @PostMapping("/multiQuestion/deleteById")
    public ApiResult deleteById(@RequestBody MultiQuestion question) {
        System.out.println("根据ID删除用户");
        multiQuestionService.deleteById(question.getQuestionId());
        return ApiResultHandler.success();
    }

    @PostMapping("/multiQuestion/update")
    public ApiResult update(@RequestBody MultiQuestion question) {
        System.out.println("更新用户信息");
        return ApiResultHandler.success(multiQuestionService.update(question));
    }

    @PostMapping("/multiQuestion/findAll")
    public ApiResult findAll() {
        System.out.println("查询全部用户");
        return ApiResultHandler.success(multiQuestionService.findAll());
    }

    @PostMapping("/multiQuestion/findById")
    public ApiResult findById(@RequestBody MultiQuestion question) {
        System.out.println("根据ID查找用户"+question.getQuestionId());
        return ApiResultHandler.success(multiQuestionService.findById(question.getQuestionId()));
    }

    @PostMapping("/multiQuestion/findOnlyId")
    public ApiResult findOnlyQuestionId() {
        MultiQuestion res = multiQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @PostMapping("/multiQuestion/findByPage")
    public ApiResult findByPage(PageBean page) {
        Page<MultiQuestion> questionPage = new Page<>(page.getPageNo(),page.getSize());
        IPage<MultiQuestion> res = multiQuestionService.findByPage(questionPage);
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @PostMapping("/multiQuestion/findByTypeAndPage")
    public ApiResult findByTypeAndPage(PageBean page,String typeId) {
        Page<MultiQuestion> questionPage = new Page<>(page.getPageNo(),page.getSize());
        IPage<MultiQuestion> res = multiQuestionService.findByTypeAndPage(questionPage,typeId);
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }
    
}
