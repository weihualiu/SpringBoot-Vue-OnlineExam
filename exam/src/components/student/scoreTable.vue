//显示学生成绩
<template>
  <div class="table">
    <p class="title">我的分数</p>
    <section class="content-el">
      <el-table ref="filterTable" :data="pagination.records" v-loading="loading">
        <el-table-column
          prop="answerTime"
          label="考试日期"
          sortable
          width="300"
          column-key="answerDate"
          :filters="filter"
          :filter-method="filterHandler">
        </el-table-column>
        <el-table-column
          prop="paperTitle"
          label="试卷名称"
          width="300">
        </el-table-column>
        <el-table-column prop="score" label="考试分数(百分制)" width="200"></el-table-column>
      </el-table>
      <el-row type="flex" justify="center" align="middle" class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[4,6,8,10]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </el-row>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagination: { //分页后的留言列表
        current: 1, //当前页
        total: null, //记录条数
        size: 10 //每页条数
      },
      loading: false, //加载标识符
      filter: null //过滤参数
    }
  },
  created() {
    this.getScore()
    this.loading = true //数据加载则遮罩表格
  },
  methods: {
    getScore() {
      let studentId = this.$cookies.get("cid")
      this.$axios({
        url: `/api/exam2101`,
        method: 'post',
        data: {
          "currentPage": this.pagination.current,
          "pageSize": this.pagination.size
        }
      }).then(res => {
        if(res.data.code == 200) {
          this.loading = false //数据加载完成去掉遮罩
          this.pagination = {...res.data.data}
        }else if(res.data.code == 100) {
            this.$router.push({path: "/"})
        }
      })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getScore()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getScore()
    },
    formatter(row, column) {
      return row.address;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    }
  }
};
</script>

<style lang="scss" scoped>

.paper {
  h4 {
    cursor: pointer;
  }
}
.paper .item a {
  color: #000;
}

.pagination {
  padding-top: 20px;
}
.table {
  width: 980px;
  margin: 0 auto;
  .title {
    margin: 20px;
  }
  .content-el {
    background-color: #fff;
    padding: 20px;
  }
}
</style>
