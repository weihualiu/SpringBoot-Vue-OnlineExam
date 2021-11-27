// 点击试卷后的缩略信息
<template>
  <div id="msg">
    <div class="title">
      <span>试卷列表</span>
      <span>/  {{examData.description}}</span>
    </div>
    <div class="wrapper">
      <ul class="top">
        <li class="example">{{examData.paperManage.description}}</li>
        <li><i class="iconfont icon-pen-"></i></li>
        <li><i class="iconfont icon-share"></i></li>
        <li class="right">
          <div>
            <span class="count">总分</span>
            <span class="score">{{examData.paperManage.totalScore}}</span>
          </div>
        </li>
      </ul>
      <ul class="bottom">
        <li class="right"><el-button @click="toAnswer(examData.paperManage.paperId)">开始答题</el-button></li>
      </ul>
      <ul class="info">
        <li @click="dialogVisible = true"><a href="javascript:;"><i class="iconfont icon-info"></i>考生须知</a></li>
      </ul>
    </div>
    <div class="content">
      <el-collapse v-model="activeName" >
        <el-collapse-item class="header" name="0">
          <template slot="title" class="stitle" >
            <div class="title">
              <span>{{examData.description}}</span><i class="header-icon el-icon-info"></i>
              <span class="time">{{examData.paperManage.totalScore}}分 / {{examData.paperManage.totalTime}}分钟</span>
            </div>
          </template>
          <el-collapse class="inner">
            <el-collapse-item>
              <template slot="title" name="1">
                <div class="titlei">填空题 (共{{topicCount[0]}}题 共计{{score[0]}}分)</div>
              </template>
            </el-collapse-item>
            <el-collapse-item>
              <template slot="title" name="2">
                <div class="titlei">选择题 (共{{topicCount[1]}}题  共计{{score[1]}}分)</div>
              </template>
            </el-collapse-item>
            <el-collapse-item>
              <template slot="title" name="3">
                <div class="titlei">判断题 (共{{topicCount[2]}}题 共计{{score[2]}}分)</div>
              </template>
            </el-collapse-item>
          </el-collapse>
        </el-collapse-item>
        
      </el-collapse>
    </div>
    
  </div>
</template>

<script>
import store from '@/vuex/store'
export default {
  store,
  data() {
    return {
      dialogVisible: false, //对话框属性
      activeName: '0',  //默认打开序号
      topicCount: [],//每种类型题目的总数
      score: [],  //每种类型分数的总数
      examData: { //考试信息
        paperManage:{}
      },
      topic: {  //试卷信息

      },
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    //初始化页面数据
    init() {
      //获取路由传递过来的试卷编号
      let examCode = sessionStorage.getItem("paperId");
      console.log("paperID:" + examCode);
      this.$axios({
        url: `/api/exam2002`,
        method: 'post',
        data: {
          "paperId":examCode
        }
      }).then(res => {  //通过examCode请求试卷详细信息
        if(res.data.code == 200) {
          this.examData = { ...res.data.data};
          // 存储当前试卷题库
          sessionStorage.setItem("paperQuestions", JSON.stringify(this.examData));
          let fillRows = Object.keys(this.examData.questions.fill);
          let judgeRows = Object.keys(this.examData.questions.judge);
          let multiRows = Object.keys(this.examData.questions.multi);
          
          this.topicCount.push(fillRows.length);
          let currentScore = 0;
          for(let i = 0; i< fillRows.length; i++) {
            currentScore += this.examData.questions.fill[i].score;
          }
          this.score.push(currentScore);
          this.topicCount.push(multiRows.length);
          let currentScore1 = 0;
          for(let i = 0; i< multiRows.length; i++) {
            currentScore1 += this.examData.questions.multi[i].score;
          }
          this.score.push(currentScore1);
          this.topicCount.push(judgeRows.length);
          let currentScore2 = 0;
          for(let i = 0; i< judgeRows.length; i++) {
            currentScore2 += this.examData.questions.judge[i].score;
          }
          this.score.push(currentScore2);
        }else if(res.data.code == 100) {
            this.$router.push({path: "/"})
        }
      })
    },
    toAnswer(id) {
      this.$router.push({name:"answer"})
    },
  }
}
</script>

<style lang="scss" scoped>
.bottom {
  .right{
    .el-button{
      color: #409EFF;
      border-color: #c6e2ff;
      background-color: #ecf5ff;
    }
  }
}
.right {
  margin-left: auto;
}
.inner .contenti .question {
  margin-left: 40px;
  color: #9a9a9a;
  font-size: 14px;
}
.content .inner .titlei {
  margin-left: 20px;
  font-size: 16px;
  color: #88949b;
  font-weight: bold;
}
.content .title .time {
  font-size: 16px;
  margin-left: 420px;
  color: #999;
}
.content .stitle {
  background-color: #0195ff;
}
.content .title span {
  margin-right: 10px;
}
#msg .content .title {
  font-size: 20px;
  margin: 0px;
  display: flex;
  align-items: center;
}
.content {
  margin-top: 20px;
  background-color: #fff;
}
.content .header {
  padding: 10px 30px;
}
.wrapper .info {
  margin: 20px 0px 0px 20px;
  border-top: 1px solid #eee;
  padding: 20px 0px 10px 0px;
}
.wrapper .info a {
  color: #88949b;
  font-size: 14px;
}
.wrapper .info a:hover {
  color: #0195ff;
}
.wrapper .bottom .btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #88949b;
  border-radius: 4px;
} 
.wrapper .bottom {
  display: flex;
  margin-left: 20px;
  color: #999;
  font-size: 14px;
  align-items: center;
}
.wrapper .bottom li {
  margin-right: 14px;
}
#msg {
  background-color: #eee;
  width: 980px;
  margin: 0 auto;
}
#msg .title {
  margin: 20px;
}
#msg .wrapper {
  background-color: #fff;
  padding: 10px;
}
.wrapper .top {
  display: flex;
  margin: 20px;
  align-items: center;
}
.wrapper .top .right {
  margin-left: auto;
}
.wrapper .top .example {
  color: #333;
  font-size: 22px;
  font-weight: 700;
}
.wrapper .top li i {
  margin-left: 20px;
  color: #88949b;
}
.wrapper .right .count {
  margin-right: 60px;
  color: #fff;
  padding: 4px 10px;
  background-color: #88949b;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  border: 1px solid #88949b;
}
.wrapper .right .score {
  position: absolute;
  left: 53px;
  top: -5px;
  padding: 1px 12px;
  font-size: 20px;
  color: #88949b;
  border: 1px dashed #88949b;
  border-left: none;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  font-weight: bold;
}
.wrapper .right div {
  position: relative;
}
</style>
