import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login', //登录界面
      component: () => import('@/components/common/login')
    },
    {
      path: '/index', //教师主页
      component: () => import('@/components/admin/index'),
      children: [
        {
          path: '/', //首页默认路由
          component: () => import('@/components/common/hello')
        },
        {
          path:'/grade', //学生成绩
          component: () => import('@/components/charts/grade')
        },
        {
          path: '/selectExamToPart', //学生分数段
          component: () => import('@/components/teacher/selectExamToPart')
        },
        {
          path: '/scorePart',
          component: () => import('@/components/charts/scorePart')
        },
        {
          path: '/allStudentsGrade', //所有学生成绩统计
          component: () => import('@/components/teacher/allStudentsGrade')
        },
        {
          path: '/selectExam', //查询所有考试
          component: () => import('@/components/teacher/selectExam')
        },
        {
          path: '/addExam', //添加考试
          component: () => import('@/components/teacher/addExam')
        },
        {
          path: '/selectAnswer', //查询所有题库
          component: () => import('@/components/teacher/selectAnswer')
        },
        {
          path: '/addAnswer', //增加题库主界面
          component: () => import('@/components/teacher/addAnswer')
        },
        {
          path: '/addAnswerChildren', //点击试卷跳转到添加题库页面
          component: () => import('@/components/teacher/addAnswerChildren')
        },
        {
          path: '/userManage',
          component: () => import('@/components/admin/userManage')
        },
        {
          path: '/addUser',
          component: () => import ('@/components/admin/addUser')
        },
        {
          path: '/questionChapterManage',
          component: () => import('@/components/admin/questionChapterManage')
        },
        {
          path: '/addQuestionChapter',
          component: () => import ('@/components/admin/addQuestionChapter')
        }
      ]
    },
    {
      path: '/student',
      component: () => import('@/components/student/index'),
      children: [
        {path:"/",component: ()=> import('@/components/student/myExam')},
        {path:'/startExam', component: () => import('@/components/student/startExam')},
        {path: '/manager', component: () => import('@/components/student/manager')},
        {path: '/examMsg', name:"examMsg", component: () => import('@/components/student/examMsg')},
        {path: '/studentScore', component: () => import("@/components/student/answerScore")},
        {path: '/scoreTable', component: () => import("@/components/student/scoreTable")}
      ]
    },
    {path: '/answer', name: "answer", component: () => import('@/components/student/answer')}
  ]
})
