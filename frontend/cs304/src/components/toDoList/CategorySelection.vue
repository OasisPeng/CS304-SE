<template>
    <v-app>
      <v-main>
        <v-container class="pa-3" fluid>
            
          <v-row class="mb-5" align="center" justify="center">
            <v-col cols="12" class="text-center">
              <v-icon large>mdi-format-list-bulleted-type</v-icon>
              <h1>请选择您的代办事项类别</h1>
            </v-col>
          </v-row>
  
          
          <v-row class="mb-5" align="center" justify="space-around">
            <v-col
              cols="12"
              md="4"
              v-for="category in categories"
              :key="category.name"
            >
              <v-btn
                block
                large
                color="primary"
                class="category-btn"
                @click="selectCategory(category.name)"
              >
                <v-icon left>{{ category.icon }}</v-icon>
                {{ category.name }}
              </v-btn>
            </v-col>
          </v-row>
  
          
          <v-row align="end" justify="center">
            <v-col cols="12" class="text-center">
              <v-btn color="red" dark @click="cancel">取消</v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
  export default {
    data() {
      return {
        categories: [
          { name: "学习", icon: "mdi-school" },
          { name: "运动", icon: "mdi-run" },
          { name: "社交", icon: "mdi-account-group" },
          { name: "饮食", icon: "mdi-food" },
          { name: "游戏", icon: "mdi-gamepad-variant" },
          { name: "阅读", icon: "mdi-book-open" },
          { name: "情感", icon: "mdi-heart" },
        ],
      };
    },
    methods: {
      selectCategory(category) {
        localStorage.setItem("category", category);
        this.$router.push("/EmotionSelection");
      },
      cancel() {
        localStorage.clear();
        this.$router.push("/");
      },
    },
  };
  </script>
  
  <style scoped>
  .category-btn {
    transition: transform 0.3s ease;
  }
  
  .category-btn:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 6px rgba(0,0,0,0.2);
  }
  
  /* 确保内容适应不同屏幕尺寸 */
  @media (max-width: 600px) {
    .category-btn {
      margin-bottom: 20px;
    }
  }
  </style>
  