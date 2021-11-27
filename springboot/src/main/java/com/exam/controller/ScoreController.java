package com.exam.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.Score;
import com.exam.entity.ScorePaperBean;
import com.exam.entity.User;
import com.exam.serviceimpl.ScoreServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @GetMapping("/scores")
    public ApiResult findAll() {
        List<Score> res = scoreService.findAll();
        return ApiResultHandler.buildApiResult(200, "查询所有考试用户成绩", res);
    }

    // 查询考试用户的试卷列表
    @PostMapping("/exam2101")
    public ApiResult findById(@RequestBody String body, HttpServletRequest request) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        Long page = jsonObject.getLong("currentPage");
        Long size = jsonObject.getLong("pageSize");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(session.getId());
        Page<ScorePaperBean> scorePage = new Page<>(page, size);
        IPage<ScorePaperBean> res = scoreService.findByUserId(scorePage, user.getUserId());
        return ApiResultHandler.buildApiResult(200, "根据用户序号查询成绩", res);
    }

    //    不分页
    @GetMapping("/score/users/{userId}")
    public ApiResult findById(@PathVariable("userId") Integer userId) {
        List<Score> res = scoreService.findById(userId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "根据用户序号查询成绩", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "用户考试记录不存在", res);
        }
    }

    //    分页
    @GetMapping("/score/papers/{page}/{size}/{paperId}")
    public ApiResult findByPaperId(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("paperId") Integer paperId) {
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findByPaperId(scorePage, paperId);
        return ApiResultHandler.buildApiResult(200, "根据试卷编号查询成绩", res);
    }

    //    不分页
    @GetMapping("/score/papers/{paperId}")
    public ApiResult findByPaperId(@PathVariable("paperId") Integer paperId) {
        List<Score> res = scoreService.findByPaperId(paperId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "根据试卷编号查询成绩", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "试卷记录不存在", res);
        }
    }
}
