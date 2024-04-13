<template>
    <v-app>
      <v-app-bar app>
        <v-spacer></v-spacer>
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon>mdi-account-circle</v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item @click="goToProfile">
              <v-list-item-title>个人信息</v-list-item-title>
            </v-list-item>
            <v-list-item @click="logout">
              <v-list-item-title>退出登录</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-app-bar>
  
      <v-main>
        <v-container>
          <v-row justify="space-around">
            <v-col cols="6" md="3" @click="navigateTo('schedule')">
              <v-card class="pa-5" outlined tile>
                <v-icon large>mdi-calendar</v-icon>
                <v-card-title>课表</v-card-title>
              </v-card>
            </v-col>
            <v-col cols="6" md="3" @click="navigateTo('course-info')">
              <v-card class="pa-5" outlined tile>
                <v-icon large>mdi-book-open-page-variant</v-icon>
                <v-card-title>课程信息</v-card-title>
              </v-card>
            </v-col>
            <v-col cols="6" md="3" @click="navigateTo('DoList')">
              <v-card class="pa-5" outlined tile>
                <v-icon large>mdi-checkbox-marked-outline</v-icon>
                <v-card-title>待办事项</v-card-title>
              </v-card>
            </v-col>
            <v-col cols="6" md="3" @click="navigateTo('forum')">
              <v-card class="pa-5" outlined tile>
                <v-icon large>mdi-forum</v-icon>
                <v-card-title>论坛</v-card-title>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
  export default {
    methods: {
      navigateTo(route) {
        this.$router.push({ name: route });
      },
      goToProfile() {
        // 导航到个人信息界面
        this.navigateTo('profile');
      },
      logout() {
        // 执行退出登录逻辑
      }
    }
  };
  </script>
  
  <style scoped>
  </style>
  