
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
