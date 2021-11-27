package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.QuestionChapter;
import com.exam.serviceimpl.QuestionChapterServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionChapterController {

    private QuestionChapterServiceImpl questionChapterService;

    @Autowired
    public QuestionChapterController(QuestionChapterServiceImpl questionChapterService){
        this.questionChapterService = questionChapterService;
    }

    @PostMapping("/questionChapter/add")
    public ApiResult add(@RequestBody QuestionChapter chapter) {
        System.out.println("添加题组");
        return ApiResultHandler.success(questionChapterService.add(chapter));
    }

    @PostMapping("/questionChapter/deleteById")
    public ApiResult deleteById(@RequestBody QuestionChapter chapter) {
        System.out.println("根据ID删除题组");
        //先删除子章节
        List<QuestionChapter> chapters = questionChapterService.findLeafById(chapter.getTypeId());
        for(QuestionChapter child:chapters){
            questionChapterService.deleteById(child.getTypeId());
        }
        //然后删除父章节
        questionChapterService.deleteById(chapter.getTypeId());
        return ApiResultHandler.success();
    }

    @PostMapping("/questionChapter/update")
    public ApiResult update(@RequestBody QuestionChapter chapter) {
        System.out.println("更新用户信息");
        return ApiResultHandler.success(questionChapterService.update(chapter));
    }

    @PostMapping("/questionChapter/findAll")
    public ApiResult findAll() {
        return ApiResultHandler.success(questionChapterService.findAll());
    }

    @PostMapping("/questionChapter/findById")
    public ApiResult findById(@RequestBody QuestionChapter chapter) {
        return ApiResultHandler.success(questionChapterService.findById(chapter.getTypeId()));
    }

    @PostMapping("/questionChapter/findByParentId")
    public ApiResult findByParentId(@RequestBody QuestionChapter chapter) {
        return ApiResultHandler.success(questionChapterService.findByParentId(chapter.getTypeId()));
    }
}
