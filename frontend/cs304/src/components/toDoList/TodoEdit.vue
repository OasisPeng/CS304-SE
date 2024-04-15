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
  </v-app>
</template>

<script>
import { saveEvent } from '../../api/index';
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
      owner: '1',
      finish: 0,
      dialog: false,
      editingLabel: '',
      items: [],
    };
  },
  mounted() {
    // 从 localStorage 中获取已选择的标签

    this.title = localStorage.getItem("title") || '';
    this.text = localStorage.getItem("text") || '';
    this.finish = localStorage.getItem("finish") || 0;
    this.level = localStorage.getItem("level") || '不重要不紧急';
    this.category = localStorage.getItem("category") || '学习';
    this.emotion = localStorage.getItem("emotion") || '开心😀';
    this.level = localStorage.getItem("level") || '不重要不紧急';
  },
  methods: {
    saveTodo() {
      const todoData = {
        title: this.title,
        text: this.text,
        owner: this.owner,
        week: this.week,
        xq: this.xq,
        category: this.category,
        emotion: this.emotion,
        level: this.level,
        finish: 0,
      };
      console.log("Saving todo:", todoData);
      saveEvent(todoData);
      this.$router.push("/");
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
      }
      this.dialog = false;
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
  background-image: url('../../assets/bg5.jfif');
  background-size: cover;
}
</style>
