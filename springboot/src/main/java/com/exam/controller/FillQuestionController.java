package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.FillQuestion;
import com.exam.entity.PageBean;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FillQuestionController {

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @PostMapping("/fillQuestion/add")
    public ApiResult add(@RequestBody FillQuestion fillQuestion) {
        int res = fillQuestionService.add(fillQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    @PostMapping("/fillQuestion/deleteById")
    public ApiResult deleteById(@RequestBody FillQuestion question) {
        System.out.println("根据ID删除用户");
        fillQuestionService.deleteById(question.getQuestionId());
        return ApiResultHandler.success();
    }

    @PostMapping("/fillQuestion/update")
    public ApiResult update(@RequestBody FillQuestion question) {
        System.out.println("更新用户信息");
        return ApiResultHandler.success(fillQuestionService.update(question));
    }

    @PostMapping("/fillQuestion/findAll")
    public ApiResult findAll() {
        System.out.println("查询全部用户");
        return ApiResultHandler.success(fillQuestionService.findAll());
    }

    @PostMapping("/fillQuestion/findById")
    public ApiResult findById(@RequestBody FillQuestion question) {
        System.out.println("根据ID查找用户"+question.getQuestionId());
        return ApiResultHandler.success(fillQuestionService.findById(question.getQuestionId()));
    }

    @PostMapping("/fillQuestion/fillQuestionId")
    public ApiResult findOnlyQuestionId() {
        FillQuestion res = fillQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @PostMapping("/fillQuestion/findByPage")
    public ApiResult findByPage(PageBean page) {
        Page<FillQuestion> questionPage = new Page<>(page.getPageNo(),page.getSize());
        IPage<FillQuestion> res = fillQuestionService.findByPage(questionPage);
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @PostMapping("/fillQuestion/findByTypeAndPage")
    public ApiResult findByTypeAndPage(PageBean page,String typeId) {
        Page<FillQuestion> questionPage = new Page<>(page.getPageNo(),page.getSize());
        IPage<FillQuestion> res = fillQuestionService.findByTypeAndPage(questionPage,typeId);
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

}
