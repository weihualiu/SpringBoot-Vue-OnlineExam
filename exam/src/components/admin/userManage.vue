// 用户管理页面
<template>
  <div class="all">
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="loginName" label="账号" width="180"></el-table-column>
      <el-table-column fixed="left" prop="userName" label="姓名" width="180"></el-table-column>
      <el-table-column prop="gender" label="性别" width="120"></el-table-column>
      <el-table-column prop="email" label="email" width="120"></el-table-column>
      <el-table-column prop="pwd" label="密码" width="120"></el-table-column>
      <el-table-column prop="role" label="角色" width="120"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="checkGrade(scope.row.userId)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteById(scope.row.userId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6, 10]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      class="page">
    </el-pagination>
    <!-- 编辑对话框-->
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="账号">
            <el-input v-model="form.loginName" prop=""></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="form.userName"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="form.gender"></el-input>
          </el-form-item>
          <el-form-item label="email">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.pwd"></el-input>
          </el-form-item>
          <el-form-item label="角色">
            <el-input v-model="form.role"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagination: {
        //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 10, //每页条数
      },
      dialogVisible: false, //对话框
      form: {}, //保存点击以后当前试卷的信息
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      //分页查询用户信息
      this.$axios({
        url:`/api/user/findByPage`,
        method:'post',
        data:{
            pageNo:this.pagination.current,
            size:this.pagination.size
        }
        }).then(res => {
        this.pagination = res.data.data;
      }).catch(error => {});
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getUserInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getUserInfo();
    },
    checkGrade(userId) { //修改用户信息
      this.dialogVisible = true
      this.$axios({
        url:`/api/user/findById`,
        method:"post",
        data:{
          "userId":userId
        }
      }).then(res => {
        this.form = res.data.data
      })
    },
    deleteById(userId) { //删除当前用户
      this.$confirm("确定删除当前教师吗？删除后无法恢复","Warning",{
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着吧',
        type: 'danger'
      }).then(()=> { //确认删除
        this.$axios({
          url: `/api/user/deleteById`,
          method: 'post',
          data:{
            "userId":userId
          }
        }).then(res => {
          this.getUserInfo()
        })
      }).catch(() => {

      })
    },
    submit() { //提交更改
      this.dialogVisible = false
      this.$axios({
        url: '/api/user/update',
        method: 'post',
        data: {
          ...this.form
        }
      }).then(res => {
        console.log(res)
        if(res.data.code ==200) {
          this.$message({
            message: '更新成功',
            type: 'success'
          })
        }
        this.getUserInfo()
      })
    },
    handleClose(done) { //关闭提醒
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        }).catch(_ => {});
    },
  }
};
</script>
<style lang="scss" scoped>
.all {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit {
    margin-left: 20px;
  }
  .el-table tr {
    background-color: #dd5862 !important;
  }
}
.el-table .warning-row {
  background: #000 !important;
}

.el-table .success-row {
  background: #dd5862;
}
</style>
