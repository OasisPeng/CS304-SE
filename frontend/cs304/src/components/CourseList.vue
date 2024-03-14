<template>
  <v-container>
    <v-col class="f">
      <div class="search">
        <v-text-field
            clearable
            density="compact"
            placeholder="搜索课程、老师"
            v-model="params.value"
            outlined
        ></v-text-field>
      </div>

      <div style="width: 60px;">
        <v-btn
            @click="getData"
            variant="tonal"
            block
            class="btn  blue"
        >
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </div>

    </v-col>
    <v-col>
      <v-tabs
          v-model="tab"
          align-with-title
      >
        <v-tabs-slider></v-tabs-slider>

        <v-tab :key="'课程'">
          课程
        </v-tab>
        <v-tab :key="'Bar'">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  icon
                  v-bind="attrs"
                  v-on="on"
                  color="primary"
                  dark
              >
                筛选课程
                <v-icon>mdi-chevron-down</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item>

                <v-btn-toggle
                    v-model=" params.sort"
                    tile
                    color="blue"
                    background-color="#42A5F5"
                    group
                    @change="changeBtn"
                    class="d-flex flex-column"
                >


                  <v-subheader>筛选类别</v-subheader>
                  <v-btn
                      v-for="item in list2"
                      :key="item.value"
                      :value="item.value"
                      x-large
                  >
                    {{item.label}}
                  </v-btn>

                </v-btn-toggle>

              </v-list-item>
            </v-list>
          </v-menu>

        </v-tab>
      </v-tabs>
    </v-col>
    <v-col style="padding:0 30px;" v-if="data.tableData.length">

      <div
          class="v-btn__content "
          style="color:blue;font-size:22px"
      >
        「{{showObj.title}}」 课程列表
      </div>
      <div style="margin:20px 10px ;margin-button:5px;color:rgba(33, 37, 41, 0.75);font-size:18px">共 342 门课（当前第 {{data.page}} 页）</div>
      <v-list two-line>
        <div
            v-for="item in data.tableData"
            :key="item.id"
        >
          <v-list-item>

            <v-list-item-content>
              <v-list-item-title
                  class="v-btn__content text--lighten-1"
                  style="color:#64B5F6;font-size:17px"
                  @click="goPage(item)"
              >{{item.chineseName}}</v-list-item-title>
              <v-col>
                <v-btn
                    x-small
                    depressed
                    color="#64B5F6"
                >
                  {{item.courseCode}}
                </v-btn>
                <span style="font-size:14px;margin-left:10px">{{item.hours}}天</span>
              </v-col>

              <div style="display:flex;align-items:center" v-if="false">

                <v-rating
                    v-model="item.rating"
                    background-color="indigo lighten-3"
                    color="indigo"
                    length="5"
                    size="15"
                ></v-rating>
                <span
                    class="v-btn__content "
                    style="color:indigo;font-size:18px;margin:0"
                >10.0</span>
                <span>({{item.credits}}人评价)</span>
              </div>
              <div class="flex fl2" >
                <span>
                  课程层次
                </span>
                <span>
                教学语言
                </span>
                <span>
                 课程类别
                </span>
                <span>
                  获得学分
                </span>
              </div>
              <div class="flex  fl2" >

                <v-btn
                    depressed
                    color="primary"
                    x-small
                    style="margin-right:15px"
                >
                  {{item.trainingType}}
                </v-btn>
                <v-btn
                    depressed
                    color="primary"
                    x-small
                    style="margin-right:15px"
                >
                  {{item.language}}
                </v-btn>
                <v-btn
                    depressed
                    color="primary"
                    x-small
                    style="margin-right:15px"
                >
                  {{item.courseCategory}}
                </v-btn>
                <v-btn
                    depressed
                    color="primary"
                    x-small
                >
                  {{item.credits}}
                </v-btn>
              </div>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>
        </div>

      </v-list>
    </v-col>
    <v-col v-else style=" text-align:center">
      暂无数据！
    </v-col>
    <div class="text-center">
      <v-pagination
          @input="getData"
          v-model="data.page"
          :length="17"
          :total-visible="7"
      ></v-pagination>
    </div>
    <v-progress-circular
        indeterminate
        v-if="data.loading"
        color="primary"
        style="position:absolute;top:20%;left:50%;margin-left:-16px;"
    ></v-progress-circular>

  </v-container>
</template>

<script>
import {getCourseData} from '../api/index'
export default {
  data: () => ({
    loaded: false,
    loading: false,

    tab: "",
    text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
    items: ["课程", "Bar"],
    list2: [
      {
        label: "全部",
        value: "1",
      },
      {
        label: "课程类别",
        value: "courseCategory",
      },
      {
        label: "培养类型",
        value: "trainingType",
      },
      {
        label: "课程代码",
        value: "courseCode",
      },
      {
        label: "开课院系",
        value: "department",
      },
      {
        label: "授课教师",
        value: "teacher",
      },
    ],
    params: {
      value: "",
      sort: "1",
      type: '1',
    },
    data: {
      total: 10,
      page:1,
      loading:false,
      tableData: [
        {
          "id": 21,
          "trainingType": "研究生",
          "courseCode": "ACA6001",
          "chineseName": "Seminar",
          "englishName": "Seminar",
          "courseNature": "培养环节",
          "courseCategory": "培养环节",
          "language": "中文",
          "credits": 0,
          "hours": 32,
          "department": "研究生院",
          "teacher": "欧阳忱忱"
        },
      ],
    },
  }),
  computed: {
    showObj() {
      const item = this.list2.find((item) => item.value === this.params.type);
      const obj = {
        title: item.label,
        total: "100",
      };
      console.log(obj, 44);
      return obj;
    },
    Params() {
      const {page}=this.data
      const key=this.params.type
      const obj={
        pageSize:10,
        pageNum:page,
        [key]:this.params.value,
      }
      if(key==='1') delete obj[key]
      return obj
    }
  },
  mounted(){
    this.getData()
  },
  methods: {
    onClick() {
      this.loading = true;

      setTimeout(() => {
        this.loading = false;
        this.loaded = true;
      }, 2000);
    },
    changeBtn() {
      this.getData()
    },
    async getData(){
      window.scrollTo({

        left: 0,
        top: 0,
        behavior: 'smooth'
      });
      this.data.loading=true
      const {data}=await getCourseData(this.Params)
      this.data.tableData=data?? []
      this.data.loading=false
      console.log(data)
    },
    goPage(item){
      console.log(item)
      const {id}=item
      this.$router.push({
        path:'/Page',
        query:{
          id,
          item:JSON.stringify(item)
        }
      })
    }
  },
};
</script>
<style  scoped>
.fl2{

  margin-top:10px;font-size:15px;justify-content:space-evenly !important
}
.v-btn__content {
  font-weight: bold !important;
}
.f {
  margin: 20px 10%;
  margin-right: 0;
  display: flex;
  height: 55px;
  /* justify-content: space-between; */
  /* align-items: center; */
}
.search {
  height: 100%;
  width: 60%;
}
.btn {
  height: 55px !important;
  width: 60px;
  /* height: px; */
}
.flex{
  display: flex;

}
</style>

