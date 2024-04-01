import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '@/views/HomePage.vue';
import ClassSchedulePage from './components/scheduletable/ClassSchedule.vue';
import CategorySelection from '@/components/toDoList/CategorySelection.vue'
import EmotionSelection from '@/components/toDoList/EmotionSelection.vue'
import LevelSelection from '@/components/toDoList/LevelSelection.vue'
import TodoEdit from '@/components/toDoList/TodoEdit.vue'
import DoList from "@/components/toDoList/DoList.vue";

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
    },
    {
      path: '/Course',
      name: 'Course',
      component:()=>import('./components/CourseList.vue')
    },
    {
      path: '/Page',
      name: 'Page',
      component:()=>import('./components/CoursePage.vue')
    },
    {
      path: '/CategorySelection',
      name: 'CategorySelection',
      component: CategorySelection
    },
    {
      path: '/EmotionSelection',
      name: 'EmotionSelection',
      component: EmotionSelection
    },
    {
      path: '/LevelSelection',
      name: 'LevelSelection',
      component: LevelSelection
    },
    {
      path: '/TodoEdit',
      name: 'TodoEdit',
      component: TodoEdit
    },
    {
      path: '/DoList',
      name: 'DoList',
      component: DoList
    }
  ]
});
