<template>
  <v-app>
    <v-main>
      <v-container class="pa-3" fluid>
        <div style="padding-top: 25px;">
          <v-row class="mb-3">
            <v-col cols="7">
              <v-text-field
                label="代办事项标题"
                v-model="title"
                prepend-icon="mdi-format-title"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
          
          <v-row>
            <v-col>
              <v-chip outlined @click="editCategory">{{ category }}</v-chip>
              <v-chip outlined @click="editEmotion">{{ emotion }}</v-chip>
              <v-chip outlined @click="editLevel">{{ level }}</v-chip>
              <v-chip outlined @click="editWeek">第 {{ week }} 周</v-chip>
              <v-chip outlined @click="editDayOfWeek">星期 {{ xq }}</v-chip>
            </v-col>
          </v-row>
          
          <v-row class="mb-5">
            <v-col cols="12">
              <v-textarea
                label="代办事项正文"
                v-model="text"
                rows="20"
                auto-grow
                prepend-icon="mdi-text"
                outlined
              ></v-textarea>
            </v-col>
          </v-row>
          
          <v-row justify="end">
            <v-col cols="6">
            </v-col>
            <v-col cols="4">
              <v-btn color="red" dark @click="cancel">取消</v-btn>
            </v-col>
            <v-col cols="2">
              <v-btn color="green" dark @click="saveTodo">保存</v-btn>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-main>
    
    <!-- 编辑标签 -->
    <v-dialog v-model="dialog" max-width="600px">
      <v-card>
        <v-card-title class="headline">编辑{{ editingLabel }}标签</v-card-title>
        <v-card-text>
          <v-list>
            <v-list-item v-for="(item, index) in items" :key="index" @click="selectItem(item)">
              <v-list-item-avatar>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title>{{ item.name }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card-text>
        <v-card-actions>
          <v-btn color="blue darken-1" text @click="closeDialog">取消</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- 周数选择对话框 -->
    <v-dialog v-model="weekDialog" persistent max-width="290px">
      <v-card>
        <v-card-title>选择周数</v-card-title>
        <v-text-field
            v-model="weekInput"
            label="输入周数 (1-52)"
            type="number"
            @keyup.enter="setWeekFromInput"
        ></v-text-field>
        <v-card-text>
          <v-container>
            <v-row>
              <v-btn
                  v-for="n in 52"
                  :key="n"
                  @click="setWeek(n)"
                  outlined
                  color="primary"
                  class="ma-1"
              >{{ n }}</v-btn>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-btn color="blue darken-1" text @click="weekDialog = false">关闭</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </v-app>
</template>

<script>
// import { saveEvent } from '../../api/index';
export default {
  data() {
    return {
      title: '',
      text: '',
      category: '学习', // 默认标签
      emotion: '开心😀',
      level: '不重要不紧急',
      week: 1,
      xq: 1,
      owner: 'visitor',
      finish: 0,
      editingLabel: '',
      items: [],
      dialog: false,
      weekDialog: false,
      weekInput: '',
    };
  },
  mounted() {
    this.title = localStorage.getItem("title") || '';
    this.text = localStorage.getItem("text") || '';
    this.finish = localStorage.getItem("finish") || 0;
    this.owner = JSON.parse(localStorage.getItem("info")).username || 'visitor';
    this.week = localStorage.getItem("week") || '1';
    this.xq = localStorage.getItem("xq") || '1';
    this.category = localStorage.getItem("category") || '学习';
    this.emotion = localStorage.getItem("emotion") || '开心😀';
    this.level = localStorage.getItem("level") || '不重要不紧急';
    console.log("正文",this.week)
  },
  methods: {

    async saveTodo() {
      const todoData = {
        title: this.title,
        text: this.text,
        owner: this.owner,
        week: this.week,
        xq: this.xq,
        category: this.category,
        emotion: this.emotion,
        level: this.level,
        finish: this.finish,
      };
      console.log("Saving todo:", todoData);
      // saveEvent(todoData);
      console.log(JSON.parse(localStorage.getItem('info')).token)
      const response = await this.$axios.post(this.$httpUrl + '/event/save', todoData, {
        withCredentials: false,
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.getItem('info')).token}`,
        },
      });
      console.log(response.data)
      // localStorage.clear();
      localStorage.removeItem('category')
      localStorage.removeItem('emotion')
      localStorage.removeItem('level')
      await this.$router.push("/DoList");
    },
    // 打开编辑标签的对话框
    editCategory() {
      this.editingLabel = '类别';
      this.items = [
        { name: "学习", icon: "mdi-school" },
        { name: "运动", icon: "mdi-run" },
        { name: "社交", icon: "mdi-account-group" },
        { name: "饮食", icon: "mdi-food" },
        { name: "游戏", icon: "mdi-gamepad-variant" },
        { name: "阅读", icon: "mdi-book-open" },
        { name: "情感", icon: "mdi-heart" },
      ];
      this.dialog = true;
    },
    editEmotion() {
      this.editingLabel = '心情';
      this.items = [
        { name: "开心😀", icon: "mdi-emoticon-excited-outline" },
        { name: "平静😐", icon: "mdi-emoticon-neutral-outline" },
        { name: "孤独🤡", icon: "mdi-emoticon-sad-outline" },
        { name: "疲惫😵‍", icon: "mdi-emoticon-sick-outline" },
        { name: "难过😞", icon: "mdi-emoticon-cry-outline" },
      ];
      this.dialog = true;
    },
    editLevel() {
      this.editingLabel = '紧急程度';
      this.items = [
        { name: "重要紧急", icon: "alert"},
        { name: "重要不紧急", icon: "bell-alert"},
        { name: "不重要紧急", icon: "exclamation-thick"},
        { name: "不重要不紧急", icon: "progress-alert"},
      ];
      this.dialog = true;
    },
    editDayOfWeek() {
      this.editingLabel = '星期';
      this.items = [
        { name: "Sunday", value: 0, icon: "mdi-calendar-today"},
        { name: "Monday", value: 1, icon: "mdi-calendar-today" },
        { name: "Tuesday", value: 2, icon: "mdi-calendar-today" },
        { name: "Wednesday", value: 3, icon: "mdi-calendar-today" },
        { name: "Thursday", value: 4, icon: "mdi-calendar-today" },
        { name: "Friday", value: 5, icon: "mdi-calendar-today" },
        { name: "Saturday", value: 6, icon: "mdi-calendar-today" },
      ];
      this.dialog = true;
    },
    // 选择新的标签项
    selectItem(item) {
      switch (this.editingLabel) {
        case '类别':
          this.category = item.name;
          break;
        case '心情':
          this.emotion = item.name;
          break;
        case '紧急程度':
          this.level = item.name;
          break;
        case '周数':
          this.week = item.name;
          break;
        case '星期':
          this.xq = item.value;
          break;
      }
      this.dialog = false;
    },
    editWeek() {
      // this.editingLabel = '周数';
      // this.items = Array.from({ length: 52 }, (_, i) => ({ name: i + 1, icon: 'mdi-calendar-week' }));
      // this.dialog = true;
      this.weekDialog = true;
    },
    setWeek(week) {
      this.week = week;
      localStorage.setItem("week", week);
      this.weekDialog = false;
    },
    setWeekFromInput() {
      if (this.weekInput >= 1 && this.weekInput <= 52) {
        this.setWeek(this.weekInput);
      } else {
        alert("请输入有效的周数 (1-52)");
      }
    },
    closeDialog() {
      this.dialog = false;
    },
    cancel() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.pa-3 {
  opacity: 0.9;
  filter: brightness(1);
  width: 500px;
  height: 1111px;
  background-image: url('../../assets/pinkBG2.jfif');
  background-size: cover;
}
</style>
