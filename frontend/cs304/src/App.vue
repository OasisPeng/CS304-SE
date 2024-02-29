<template>
  <v-app>
    <v-main>
      <ClassSchedule/>
    </v-main>
  </v-app>
</template>

<script>


import ClassSchedule from './components/ClassSchedule.vue';

export default {
  name: 'App',

  components: {
    ClassSchedule,
  },

  data: () => ({
    //
  }),
};
</script>
