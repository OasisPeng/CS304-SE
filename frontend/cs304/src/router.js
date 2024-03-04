import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '@/views/HomePage.vue';
import ClassSchedulePage from './components/ClassSchedule.vue';

Vue.use(Router);

export default new Router({
  mode: 'history', // 使用HTML5 History模式
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage
    },
    {
      path: '/ClassSchedule',
      name: 'ClassSchedulePage',
      component: ClassSchedulePage
    }
  ]
});
