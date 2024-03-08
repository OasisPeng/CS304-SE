<template>
  <v-app>
    <v-main>
      <DoList/>
    </v-main>
  </v-app>
</template>

<script>


// import ClassSchedule from './components/ClassSchedule.vue';
//import HomePage from './views/HomePage.vue';
// import ClassSchedule from "@/components/scheduletable/ClassSchedule.vue";
import DoList from "@/components/toDoList/DoList.vue"
export default {
  name: 'App',

  components: {
    //ClassSchedule,
    // HomePage
    DoList
},

  data: () => ({
    //
  }),
};
</script>
