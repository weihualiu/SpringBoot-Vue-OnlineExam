<!-- 添加用户 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="账号">
            <el-input v-model="form.loginName"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="form.userName"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="form.gender" placeholder="请选择" @change="changeGender">
              <el-option v-for="(item,index) in gender" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="email">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.pwd"></el-input>
          </el-form-item>
          <el-form-item label="角色">
            <el-select v-model="form.role" placeholder="请选择" @change="changeRole">
              <el-option v-for="(item,index) in roles" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">立即创建</el-button>
        <el-button type="text" @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      gender: [{
            value:"男",
            label:"男"
          },{
            value:"女",
            label:"女"
          }
        ],
      roles: [{
            value:0,
            label:"管理员"
          },{
            value:1,
            label:"老师"
          },{
            value:2,
            label:"员工"
          }
        ],
      form: { //表单数据初始化
        loginName: null,
        userName: null,
        gender: "男",
        email: "123456@rytong.com",
        pwd: "123456",
        role:2
      }
    };
  },
  methods: {
    onSubmit() { //数据提交
      this.$axios({
        url: '/api/user/add',
        method: 'post',
        data: {
          ...this.form
        }
      }).then(res => {
        if(res.data.code == 200) {
          this.$message({
            message: '数据添加成功',
            type: 'success'
          })
          this.$router.push({path: '/userManage'})
        }
      })
    },
    changeGender(value){
      this.form.gender = value;
    },
    changeRole(value){
      this.form.role = value;
    },
    cancel() { //取消按钮
      this.form = {}
    },
  }
};
</script>
<style lang="scss" scoped>
.add {
  padding: 0px 40px;
  width: 400px;
}
</style>

