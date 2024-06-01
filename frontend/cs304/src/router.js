import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '@/views/HomePage.vue';
import ClassSchedule from './components/scheduletable/ClassSchedule.vue';
import CategorySelection from '@/components/toDoList/CategorySelection.vue'
import EmotionSelection from '@/components/toDoList/EmotionSelection.vue'
import LevelSelection from '@/components/toDoList/LevelSelection.vue'
import TodoEdit from '@/components/toDoList/TodoEdit.vue'
import DoList from '@/components/toDoList/DoList.vue'
import FirstPage from "@/components/second_hand/FirstPage.vue";
import Person from "@/components/second_hand/PersonProfile.vue"
import BuyPage from "@/components/second_hand/BuyPage.vue"
import FavouritePage from "@/components/second_hand/FavouritePage.vue";
import SalePage from "@/components/second_hand/SalePage.vue";
import MarketPage from "@/components/second_hand/MarketPage.vue";
import MessagesPage from "@/components/second_hand/MessagesPage";
import GoodsPage from "@/components/second_hand/GoodsPage";
import ReleasePage from "@/components/second_hand/ReleasePage";
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
      path: '/FirstPage',
      name: 'FirstPage',
      component: FirstPage
    },
    {
      path: '/GoodsPage',
      name: 'GoodsPage',
      component: GoodsPage
    },
    {
      path: '/ReleasePage',
      name: 'ReleasePage',
      component: ReleasePage
    },
    {
      path: '/BuyPage',
      name: 'BuyPage',
      component: BuyPage
    },
    {
      path: '/FavouritePage',
      name: 'FavouritePage',
      component: FavouritePage
    },
    {
      path: '/SalePage',
      name: 'SalePage',
      component: SalePage
    },
    {
      path: '/Person',
      name: 'Person',
      component: Person
    },
    {
      path: '/MarketPage',
      name: 'MarketPage',
      component: MarketPage
    },
    {
      path: '/MessagesPage',
      name: 'MessagesPage',
      component: MessagesPage
    },
    {
      path: '/ClassSchedule',
      name: 'ClassSchedule',
      component: ClassSchedule
    },
    {
      path: '/CourseList',
      name: 'CourseList',
      component:()=>import('./components/CourseList.vue')
    },
    {
      path: '/CoursePage',
      name: 'CoursePage',
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
    },
    {
      path: '/login',
      name: 'Login',

      component: ()=>import ('./components/CourseHelper.vue')
    },
    {
      path: '/Page',
      name: 'Page',

      component: ()=>import ('./components/CoursePage.vue')
    },
    {
      path: '/Chat',
      name: 'chat',

      component: ()=>import ('./components/gpt.vue')
    }
  ]
});
