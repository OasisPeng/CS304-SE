<template>
    <v-app>
      <v-container class="ok1" fluid>
        <v-container class="ok2" fluid>
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
        </v-container>

        <v-main>
          <v-container class="ok3">
            <v-row justify="space-around">
              <v-col cols="6" md="3" @click="navigateTo('ClassSchedule')">
                <v-card class="pa-5" outlined tile>
                  <v-icon large>mdi-calendar</v-icon>
                  <v-card-title>课表</v-card-title>
                </v-card>
              </v-col>
              <v-col cols="6" md="3" @click="navigateTo('CourseList')">
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
              <v-col cols="6" md="3" @click="navigateTo('FirstPage')">
                <v-card class="pa-5" outlined tile>
                  <v-icon large>mdi-forum</v-icon>
                  <v-card-title>交易市场</v-card-title>
                </v-card>
              </v-col>
            </v-row>
          </v-container>

          <v-btn
              fab
              bottom
              right
              color="teal"
              dark
              @click="navigateTo('Chat')"
              class="customer-service-button"
          >
            <v-icon>mdi-headset</v-icon>
          </v-btn>

        </v-main>
      </v-container>
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
      localStorage.clear()
      this.$router.push({ path: '/login' });
    }
  }
};
</script>

<style scoped>
/*.ok2{*/
/*  opacity: 0.7;*/
/*}*/
.ok3{
  opacity: 0.9;
  /*filter: blur(1px);*/
}
.ok1{
  width: 500px;
  height: 1111px;
  background-image: url('../assets/bg3.jpeg');
  background-size: cover;
}

.customer-service-button {
  position: fixed;
  bottom: 70px;
  right: 35px;
}
</style>
