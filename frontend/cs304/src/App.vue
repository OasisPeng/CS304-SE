<!--
 * @Author: Ren Bing
 * @Date: 2024-03-08 15:38:55
 * @LastEditors: Ren Bing
 * @LastEditTime: 2024-03-08 17:39:34
 * @Description: 请填写简介
-->
<template>

  <v-app>
    <v-content>
      <!-- <HomePage/> -->
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>


// import ClassSchedule from './components/ClassSchedule.vue';
// import HomePage from './views/HomePage.vue';

export default {
  name: 'App',

  components: {
    // ClassSchedule,
    // HomePage
  },

  data: () => ({
    //
  }),
};
</script>
<style>
*{
  margin: 0;
  padding: 0
}
</style>
