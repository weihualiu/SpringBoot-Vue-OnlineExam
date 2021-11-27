package com.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.*;
import com.exam.serviceimpl.*;
import com.exam.util.ApiResultHandler;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 试卷控制
 */
@RestController
public class PaperController {

    @Autowired
    private PaperServiceImpl paperService;

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuestionChapterServiceImpl questionChapterService;

    @Autowired
    private PaperQuestionServiceImpl paperQuestionService;

    @Autowired
    private PaperUserServiceImpl paperUserService;

    /**
     * 查询所有试卷
     * @return
     */
    @PostMapping("/exam2010")
    public ApiResult<PaperManage> findAll(@RequestBody String requestBody, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(session.getId());
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        Long page = jsonObject.getLong("currentPage");
        Long size = jsonObject.getLong("pageSize");
        Page<PaperManage> paperManagePage = new Page<>(page, size);
        ApiResult res;
        if(user.getRole().equals("2")) {
            res = ApiResultHandler.buildApiResult(200, "请求成功", paperService.findByUserId(paperManagePage, user.getUserId()));
        }else{
            res = ApiResultHandler.buildApiResult(200, "请求成功", paperService.findAll(paperManagePage));
        }

        return res;
    }

//    @PutMapping("/papers/{userId}")
//    public ApiResult update(@PathVariable("paperId") Integer paperId, PaperManage paperManage) {
//        return ApiResultHandler.success(paperService.update(paperManage));
//    }

    /**
     * 查询指定试卷详情
     * @param paperId 试卷编号
     * @return
     */
    @PostMapping("/papers/{paperId}")
    public ApiResult findById(@PathVariable("paperId") Integer paperId, HttpServletRequest request) {
        PaperManage paperManage = paperService.findById(paperId);
        List<User> users = userService.findUsersById(paperId);
        // 鉴权，如果用户没有该试卷则返回错误
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(session.getId());
        if(user.getUserId().equals("2")) {
            boolean flag = false;
            for (User element : users) {
                if (user.getUserId().equals(element.getUserId())) {
                    flag = true;
                }
            }
            if(!flag) {
                return ApiResultHandler.buildApiResult(200, "没有找到该用户的试卷记录", null);
            }
        }
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 1
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 2
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        Map<String, List<?>> map = new HashMap<>();
        map.put("fill", fillQuestionsRes);
        map.put("multi", multiQuestionRes);
        map.put("judge", judgeQuestionRes);

        PaperDetail paperDetail = new PaperDetail();
        paperDetail.setPaperManage(paperManage);
        paperDetail.setUsers(users);
        paperDetail.setQuestions(map);

        return ApiResultHandler.buildApiResult(200, "", paperDetail);
    }

    /**
     * 组卷上传
     * @param paperAutoBuild
     * @return
     */
    @PostMapping("/papers/autobuild")
    public ApiResult add(@RequestBody PaperAutoBuild paperAutoBuild) {
        PaperManage paperManage = new PaperManage();
        paperManage.setDescription(paperAutoBuild.getDescription());
        paperManage.setStartTime(paperAutoBuild.getStartTime());
        paperManage.setEndTime(paperAutoBuild.getEndTime());
        paperManage.setTotalTime(paperAutoBuild.getTotalTime());
        int res = paperService.add(paperManage);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400, "添加失败", res);

        }
        Integer paperId = paperManage.getPaperId();
        // 读取所有子章节
        List<QuestionChapter> questionChapterList = questionChapterService.findLeafById(paperAutoBuild.getSection());
        // 查询所有章节对应的题库
        List<Integer> chapterIdList = new ArrayList<>();
        for(QuestionChapter chapter : questionChapterList) {
            chapterIdList.add(chapter.getTypeId());
        }
        List<FillQuestion> fillQuestionList = fillQuestionService.findBySubject(chapterIdList);
        List<MultiQuestion> multiQuestionList = multiQuestionService.findBySubject(chapterIdList);
        List<JudgeQuestion> judgeQuestionList = judgeQuestionService.findBySubject(chapterIdList);
        List<String> list = questionConvert(fillQuestionList, multiQuestionList, judgeQuestionList);
        int nums = paperAutoBuild.getQuestionNums();
        if(list.size() > nums) {
            // 随机抽取
            list = questionRandom(list, nums);
        }
        int score = getScoreFromQuestion(list);
        paperQuestionService.add(paperId, list);
        paperManage.setTotalScore(score);
        paperService.update(paperManage);

        // 插入考试用户
        Integer[] userIds = paperAutoBuild.getUsers();
        List<PaperUser> paperUsers = new ArrayList<>();
        for(Integer userId : userIds) {
            PaperUser paperUser = new PaperUser();
            paperUser.setPaperId(paperId);
            paperUser.setUserId(userId);
            paperUsers.add(paperUser);
        }
        paperUserService.insert(paperUsers);

        return ApiResultHandler.buildApiResult(200, "添加成功", res);
    }

    /**
     * 上传试卷答案
     * @param object JSON结构数据
     * @return
     */
    @PostMapping("/papers/commit")
    public ApiResult updateByUser(@RequestBody String object) {
        Gson gson = new Gson();
        PaperAnswers answers = gson.fromJson(object, PaperAnswers.class);
        List<PaperAnswer> answerList = answers.getAnswerList();
        for(PaperAnswer answer : answerList) {
            answer.setPaperId(answers.getPaperId());
            answer.setUserId(answers.getUserId());

            // insert into db
            // start transaction

            // transaction end
        }

        return ApiResultHandler.buildApiResult(200, "提交试卷成功", null);
    }

    /**
     * 根据题库获取总分
     * @param fillQuestionList
     * @param multiQuestionList
     * @param judgeQuestionList
     * @return
     */
    private int getMaxScore(List<FillQuestion> fillQuestionList, List<MultiQuestion> multiQuestionList, List<JudgeQuestion> judgeQuestionList) {
        int score = 0;
        for(FillQuestion question : fillQuestionList) {
            score += question.getScore();
        }
        for(MultiQuestion question : multiQuestionList) {
            score += question.getScore();
        }
        for(JudgeQuestion question : judgeQuestionList) {
            score += question.getScore();
        }
        return score;
    }

    private List<String> questionConvert(List<FillQuestion> fillQuestionList, List<MultiQuestion> multiQuestionList, List<JudgeQuestion> judgeQuestionList) {
        List<String> list = new ArrayList<>();
        for(FillQuestion question : fillQuestionList) {
            list.add(question.getTypeId().toString() + "-" + question.getQuestionId().toString() + "-" + question.getScore().toString());
        }
        for(MultiQuestion question : multiQuestionList) {
            list.add(question.getTypeId().toString() + "-" + question.getQuestionId().toString() + "-" + question.getScore().toString());
        }
        for(JudgeQuestion question : judgeQuestionList) {
            list.add(question.getTypeId().toString() + "-" + question.getQuestionId().toString() + "-" + question.getScore().toString());
        }

        return list;
    }

    /**
     * 随机抽取指定数量的值
     * @param list
     * @param maxSize
     * @return
     */
    private List<String> questionRandom(List<String> list, int maxSize) {
        List<String> temp = new ArrayList<>();
        Random random = new Random();
        while(temp.size() < maxSize) {
            int r = random.nextInt(list.size());
            String str = list.get(r);
            if(!temp.contains(str)) {
                temp.add(str);
            }
        }
        return temp;
    }

    private int getScoreFromQuestion(List<String> strings) {
        int score = 0;
        for(String str : strings) {
            String[] arr = str.split("-");
            score += Integer.valueOf(arr[2]);
        }
        return score;
    }

    /**
     * 查询指定试卷详情(学生)
     * @param requestBody
     * @return
     */
    @PostMapping("/exam2002")
    public ApiResult findById(@RequestBody String requestBody, HttpServletRequest request) {
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        Integer paperId = jsonObject.getInteger("paperId");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(session.getId());

        PaperManage paperManage = paperService.findById(paperId);
        Integer count = paperUserService.findPaper(paperId, user.getUserId());
        // 鉴权，如果用户没有该试卷则返回错误
        if(count == null || count.equals(0)) {
            return ApiResultHandler.buildApiResult(200, "没有找到该用户的试卷记录", null);
        }

        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 1
        for(FillQuestion question : fillQuestionsRes) {
            question.setAnalysis(null);
            question.setAnswer(null);
        }
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 2
        for(MultiQuestion question : multiQuestionRes) {
            question.setAnalysis(null);
            question.setRightAnswer(null);
        }
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        for(JudgeQuestion question : judgeQuestionRes) {
            question.setAnalysis(null);
            question.setAnswer(null);
        }
        Map<String, List<?>> map = new HashMap<>();
        map.put("fill", fillQuestionsRes);
        map.put("multi", multiQuestionRes);
        map.put("judge", judgeQuestionRes);

        PaperDetail paperDetail = new PaperDetail();
        paperDetail.setPaperManage(paperManage);
        paperDetail.setQuestions(map);

        return ApiResultHandler.buildApiResult(200, "", paperDetail);
    }
}
