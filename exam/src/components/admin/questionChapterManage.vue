<template>
  <section class="question_chapter_manage">
    <el-cascader-panel :options="options" v-model="cascaderVal" @change="chooseOption()"></el-cascader-panel>
    <el-button type="primary" class="row-login" @click="chooseVal()">打印选中的信息</el-button>
  </section>
</template>
<script>
import store from "@/vuex/store";
import { mapState } from "vuex";

export default {
  store,
  name: "question_chapter_manage",
  data() {
    return {
      options: [
        // {
        //   value: "Andriod",
        //   label: "Andriod",
        //   children: [
        //     {
        //       value: "第1章",
        //       label: "第1章",
        //       children: [
        //         {
        //           value: "第1.1章",
        //           label: "第1.1章"
        //         }
        //       ]
        //     }
        //   ]
        // },
        // {
        //   value: "IOS",
        //   label: "IOS"
        // },
        // {
        //   value: "H5",
        //   label: "H5",
        //   children: [
        //     {
        //       value: "第1章",
        //       label: "第1章",
        //       children: [
        //         {
        //           value: "第1.1章",
        //           label: "第1.1章"
        //         }
        //       ]
        //     },
        //     {
        //       value: "第2章",
        //       label: "第2章",
        //       children: [
        //         {
        //           value: "第2.1章",
        //           label: "第2.1章",
        //           children: [
        //             {
        //               value: "第2.1.1章",
        //               label: "第2.1.1章"
        //             }
        //           ]
        //         }
        //       ]
        //     }
        //   ]
        // }
      ],
      cascaderVal: ""
    };
  },
  methods: {
    //查询根据父章节查询子章节
    findChildrenChapter(typeId) {
      this.$axios({
        url: `/api/questionChapter/findByParentId`,
        method: "post",
        data: {
          typeId: typeId
        }
      }).then(res => {
        if (res.data.code == 200) {
          let resData = res.data.data;
          resData.map((res, ind) => {
            //根节点
            if (typeId == "0") {
              this.options[ind] = {
                label: res.qTypeName,
                value: res.typeId
              };
            } else {
              this.options.map(item => {
                if (item.value == typeId) {
                  let nodes = [];
                  resData.map((node, i) => {
                    nodes[i] = {
                      label: node.qTypeName,
                      value: node.typeId
                    };
                  });
                  console.log("nodes=", nodes);
                  item.children = [...nodes];
                }
              });
            }
          });
          this.options = [...this.options];
          console.log("options=", this.options);
        } else if (res.data.code == 100) {
          this.$router.push({ path: "/" });
        } else {
          this.$message({
            showClose: true,
            type: "error",
            message: "接口失败，请稍后再试"
          });
        }
      });
    },
    chooseOption() {
      let typeId = this.cascaderVal[this.cascaderVal.length - 1] + "";
      console.log("chooseOptionl=", typeId);
      this.findChildrenChapter(typeId);
    }
  },
  created() {
    this.findChildrenChapter("0");
  },
  //   computed: mapState(["userInfo"]),
  mounted() {}
};
</script>

<style lang="scss" scoped>
</style>
