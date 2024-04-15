<template>
  <v-container>
    <v-col class="flex">
      <v-btn
          icon
          @click="$router.go(-1)"
      >
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <div
          class="v-btn__content "
          style="color:blue;font-size:22px;margin-left:50px"
      >
        {{pageInfo.chineseName}}
      </div>
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
    </v-col>
    <v-divider></v-divider>
    <v-col>
      <v-col>
        <v-btn
            x-small
            depressed
            color="#64B5F6"
        >
          {{ pageInfo.courseCode}}
        </v-btn>
        <span style="font-size:14px;margin-left:10px">{{ pageInfo.hours}}天</span>
      </v-col>

      <div
          style="display:flex;text-align:center"
          v-if="false"
      >

        <v-rating
            v-model=" pageInfo.rating"
            background-color="indigo lighten-3"
            color="indigo"
            length="5"
            size="15"
        ></v-rating>
        <span
            class="v-btn__content "
            style="color:indigo;font-size:18px;margin:0"
        >10.0</span>
        <span>({{ pageInfo.credits}}人评价)</span>
      </div>
      <div class="flex fl2 h">
        <span>
          教师
        </span>
        <span>
          学院
        </span>
        <span>
          英文名称
        </span>
        <span>
          环节
        </span>
      </div>
      <div class="flex  fl2 h">

        <v-btn
            depressed
            color="primary"
            x-small
            style="margin-bottom:10px"
        >
          1.{{pageInfo.teacher}}
        </v-btn>
        <v-btn
            depressed
            color="primary"
            x-small
            style="margin-bottom:10px"
        >
          2.{{pageInfo.department}}
        </v-btn>
        <v-btn
            depressed
            color="primary"
            x-small
            style="margin-bottom:10px"
        >
          3.{{pageInfo.englishName}}
        </v-btn>
        <v-btn
            depressed
            color="primary"
            x-small
            style="margin-bottom:10px"
        >
          4.{{pageInfo.courseNature??'__'}}
        </v-btn>
      </div>
    </v-col>
    <v-col class="flex h">
      <div class="h-l">
        <p> <v-icon color="#000">mdi-file-multiple</v-icon>选课类别: </p><span>{{pageInfo.courseCode}}</span>
      </div>
      <div class="h-l">
        <p> <v-icon color="#000">mdi-file-multiple</v-icon>教学语言: </p> <span>{{pageInfo.language}}</span>
      </div>
      <div class="h-l">
        <p> <v-icon color="#000">mdi-menu</v-icon>课程类别:</p> <span>{{pageInfo.courseCategory}}</span>
      </div>
      <div class="h-l">
        <p> <v-icon color="#000">mdi-school-outline</v-icon>课程层次: </p> <span>{{pageInfo.trainingType}}</span>
      </div>
      <div class="h-l">
        <p> <v-icon color="#000">mdi-bank</v-icon>开课单位: </p><span>{{pageInfo.courseCategory}}</span>
      </div>
      <div class="h-l">
        <p> <v-icon color="#000">mdi-forum</v-icon>获得学分: </p><span>{{pageInfo.credits}}</span>
      </div>
    </v-col>
    <v-col>
      <strong>课程主页</strong><span>
        ：暂无（如果你知道，请点右上角“编辑课程信息”添加！）
      </span>
    </v-col>
    <v-col>
      <div class="flex  fl2 h">

        <v-btn
            depressed
            color="primary"
            small
            class="m-b"
            outlined
            style="margin-right:15px"
        >
          <v-icon>
            mdi-dialpad
          </v-icon>
          课程大纲PDF
        </v-btn>
        <v-btn
            outlined
            class="m-b"
            depressed
            color="primary"
            small
            @click="dianZan"
            style="margin-right:15px"
        >
          <v-icon :color="isZan?'red':''">
            mdi-heart
          </v-icon>
          点赞
        </v-btn>
        <v-btn
            v-if="false"
            depressed
            color="primary"
            small
            class="m-b"
            outlined
            style="margin-right:15px"
        >
          <v-icon>mdi-thumb-up</v-icon>
          推荐（0）
        </v-btn>
        <v-btn
            v-if="false"
            depressed
            color="primary"
            small
            class="m-b"
            outlined
        >
          <v-icon>mdi-thumb-down</v-icon>
          不推荐（10）
        </v-btn>
      </div>
    </v-col>
    <v-col>
      <div
          style="display:flex;text-align:center"
          class="flex h"
          v-if="true"
      >
        <div style="margin-top:3px">
          <strong>评分:</strong>
        </div>

        <v-rating
            v-model=" pageInfo.rating"
            background-color="indigo lighten-3"
            color="indigo"
            length="5"
            size="15"
            clearable
            @click.native="handleRatingChange"
        ></v-rating>
        <span
            class="v-btn__content "
            style="color:indigo;font-size:18px;margin:0"
        ></span>
        <span>({{ pageInfo.rating || pageInfo.credits}}分)</span>
      </div>
    </v-col>
    <v-col v-if="false">
      <div
          class="v-btn__content "
          style="color:#337ab7;font-size:22px;margin-bottom:8px"
      >
        课程简介（
        {{pageInfo.courseCategory}}）
      </div>
      <v-divider></v-divider>
      <div style="padding:10px">
        {{text}}
      </div>
      <v-btn
          depressed
          height="60px"
          width="100%"
          x-small
          @click="change=!change"
          :outlined="change"
          color="primary"
      >
        <v-icon>
          mdi-dialpad
        </v-icon>&nbsp; 展开英文描述 Expand English Description
      </v-btn>
      <div
          style="padding:10px"
          v-show="!change"
      >
        {{es}}
      </div>
    </v-col>
    <v-col>
      <div
          class="v-btn__content  flex f-c"
          style="color:#337ab7;font-size:22px;margin-bottom:8px"
      >
        <span> 广场</span>
        <v-btn
            depressed
            color="primary"
            small
            class="m-b"
            outlined
            @click="dialog=true"
        >
          <v-icon>mdi-pencil</v-icon>
          写评论
        </v-btn>
      </div>
    </v-col>
    <v-col>
      <div v-if="!ContentList.length" style="text-align:center">
        暂无评论
      </div>
      <div class="pinglun" v-else v-for="item in ContentList" :key="item.userId+Math.random()">
        <div class="img" >

          <img :src="item.img" alt="">
          <p class="name" v-if="item.userId<10000">
            {{item.userId}}
          </p>
          <p class="name" v-else>
            匿名{{item.userId}}
          </p>
          <div class="img-delete" style="" @click="openDel(item)">
            <v-icon>
              mdi-delete-outline
            </v-icon>

          </div>
        </div>

        <div class="c">{{item.content}}</div>
        <div class="footer"></div>
      </div>
    </v-col>
    <!-- 写评论 -->
    <v-row justify="center">
      <v-dialog
          v-model="dialog"
          persistent
          max-width="290"
      >

        <v-card>
          <v-card-title class="text-h5">
            写评论：
          </v-card-title>
          <v-card-text xs3>
            匿名：
            <v-btn-toggle rounded  v-model.lazy="box">
              <v-btn  small :value="true"

              >是</v-btn>
              <v-btn  small :value="false">否</v-btn>
            </v-btn-toggle>
          </v-card-text>
          <v-card-text> <v-textarea
              v-model="content"
              auto-grow
              filled
              color="deep-purple"
              label="请输入评论"
              rows="1"
          ></v-textarea></v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="green darken-1"
                text
                @click="dialog = false"
            >
              取消
            </v-btn>
            <v-btn
                color="green darken-1"
                text
                @click="savaContent"
            >
              提交
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    <v-row>
      <v-dialog
          v-model="delDialog"
          persistent
          max-width="290"
      >

        <v-card>
          <v-card-title class="text-h5">
            提示：
          </v-card-title>
          <v-card-text xs3>
            确认要删除此条评论？
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="green darken-1"
                text
                @click="delDialog = false"
            >
              取消
            </v-btn>
            <v-btn
                color="green darken-1"
                text
                @click="delContent"
            >
              删除
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    <v-progress-circular
        indeterminate
        v-if="loading"
        color="primary"
        style="position:absolute;top:50%;left:50%;margin-left:-16px;"
    ></v-progress-circular>
  </v-container>
</template>

<script>
import img1 from "../assets/1.jpg";
import img2 from "../assets/2.jpg";
import img3 from "../assets/3.jpg";
import img4 from "../assets/4.jpg";
import {
  saveZan,
  deleteZan,
  saveFen,
  deleteFen,
  saveContent,
  getContentList,
  deleteContent
} from "../api/index";
export default {
  data: () => ({
    pageInfo: {
      id: 1,
      trainingType: "研究生",
      courseCode: "025100mc29",
      chineseName: "区块链技术的金融应用",
      englishName: "025100mc29",
      courseNature: null,
      courseCategory: "校外共享课",
      language: "中文",
      credits: 2,
      hours: 40,
      department: "暨南大学深圳校区",
      teacher: "周德华",
    },
    change: true,
    isZan: false,
    dialog: false,
    delDialog:false,
    delItem:null,
    loading: false,
    content: "",
    ContentList:[],
    box:false,
    imgArr:[img1,img2,img3,img4],
    es: " This experimental course is aimed at the junior students. A series of basic experiments are set up, involving information materials, energy materials, biomedical materials, advanced processing and manufacturing of materials, as well as the application of these materials and technologies in information, energy, biomedical, aerospace and other fields. Through both professional and interesting experiments, the basic experimental operation skills of students are cultivated, and the understanding of basic theory courses is improved. If the content of the experiment is properly expanded, it can be combined with the college scientific and technological activities and lay a foundation for students to carry out extracurricular scientific and technological innovation activities. At the same time, students will have a preliminary understanding of the connotation of materials discipline and the characteristics of related disciplines, to help students major selection. ",
    text: " 本实验课程面向全校低年级学生，设置一系列基础实验，利用奇妙的材料制备能够在生活中使用的趣味性作品。涉及信息材料、能源材料、生物医用材料、材料先进加工和制造，以及这些材料和技术在信息、能源、生物医疗、航空航天等领域的应用。通过兼具专业性、趣味性和实用性的实验，培养学生的基本实验操作技能，辅助基础理论课程理解。实验内容适当拓展，即可与书院学生科技活动结合，为学生开展课外科技创新活动打下基础。同时，学生将初步了解材料学科的内涵，以及相关学科的特点，为学生专业选择提供帮助。 ",
  }),
  watch: {
    // 当导航路径切换变化时，执行
    "$route.query": {
      // 处理器
      handler(newVal) {
        // console.log('路径变化了:', newVal.query.id)
        // 获取路径中传递的查询字符串参数
        const { id, item } = newVal;
        console.log(id, this.pageInfo);
        this.pageInfo = JSON.parse(item);
        // 根据 id 查询
        this.getContentList()
      },
      immediate: true, // 组件实例创建时，立刻调用 handler 处理器
    },
  },
  mounted() {},
  methods: {
    async dianZan() {
      this.isZan = !this.isZan;
      const api = this.isZan ? saveZan : deleteZan;
      const { id } = this.pageInfo;
      const params = {
        // "id": id,
        courseId: id,
        // "zan": 1,
        userId: 22,
      };

      const res = await api(params);
      console.log("44", api, res, deleteFen);
    },
    async handleRatingChange() {
      const api = saveFen;
      const { id } = this.pageInfo;
      const params = {
        courseId: id,
        score: this.pageInfo.rating,
        userId: 22,
      };
      const res = await api(params);
      console.log(this.pageInfo.rating,res);
    },
    openDialog() {
      this.content = "";
      this.dialog = true;
    },
    openDel(item){
      this.delItem=item
      this.delDialog=!this.delDialog
    },
    async delContent(){
      this.delDialog = false;


      const params = {
        ...this.delItem,img:undefined
      };
      const {code,msg} = await deleteContent(params);
      if(code===200){
        console.log(msg)
        this.getContentList()
      }
    },
    async savaContent() {
      this.dialog = false;
      const { id } = this.pageInfo;
      const userId=this.box? 11100+Math.floor(Math.random() * 10000):  Math.floor(Math.random() * 10000)
      const params = {
        courseId: id,
        content: this.content,
        // "zan": 1,
        // url:'222',
        userId
      };
      const {code,msg} = await saveContent(params);
      if(code===200){
        console.log(msg)
        this.getContentList()
      }
    },
    async getContentList(){
      this.loading=true
      const {code ,data}=await getContentList(this.pageInfo.id)
      if(code===200){
        this.ContentList=data.map(i=>({...i,img:this.getRandomElement()}))
      }
      this.loading=false
      console.log(code,this.ContentList)
    },
    getRandomElement() {
      const arr=this.imgArr
      const index = Math.floor(Math.random() * arr.length);
      return arr[index];
    }
  },
};
</script>
<style scoped>
.flex {
  display: flex;
}
.h {
  flex-wrap: wrap;
  justify-content: space-around;
}
.f-c {
  justify-content: space-between;
  align-items: center;
}
.h-l {
  color: #000;
  font-weight: 300;
  /* width: 45%; */
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
}
.m-b {
  margin-bottom: 10px;
}
.fl2 {
  margin-top: 10px;
  font-size: 15px;
  justify-content: space-evenly !important;
}
.v-btn__content {
  font-weight: bold !important;
}
.pinglun{
  min-height: 200px;
  border-radius: 5px;
  /* background-color: aqua; */
  margin-top: 10px;
  box-shadow: #000;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border: 1px solid #ccc;
}
.c{
  min-height: 80px;
  padding: 10px;
  flex: 1;
}
.name{
  color: #337ab7;
  font-size: 16px;
  margin-top: 10px;
  margin-left: 20px;
}
.img{
  width: 100%;
  background-color: #e9ecef;
  display: flex;
  position: relative;
  align-items: center;
  padding: 0 20px;
}
.img-delete{
  position: absolute;
  right: 10px;
  cursor: pointer;
}
img{
  width: 30px;
  height: 20px;
}
.footer{
  /* border-bottom-left-radius: 20%; */
  background-color: #e9ecef;
  height: 15px;
}
</style>