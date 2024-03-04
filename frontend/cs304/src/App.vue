<template>
  <v-app>
    <v-main>
      <HomePage/>
    </v-main>
  </v-app>
</template>

<script>


// import ClassSchedule from './components/ClassSchedule.vue';
import HomePage from './views/HomePage.vue';

export default {
  name: 'App',

  components: {
    // ClassSchedule,
    HomePage
},

  data: () => ({
    //
  }),
};
</script>
