/*
 * @Description: 路由级权限控制
 * @Author: Dong Wei
 * @Date: 2022-05-09 16:53:40
 * @LastEditors: Ren Bing
 * @LastEditTime: 2023-02-13 09:46:19
 * @FilePath: \ky-cdfu-FE\src\permission.js
 */
import router from './router.js';

router.beforeEach((to, from, next) => {
  const token= JSON.parse(localStorage.getItem('info'))?.token ?? ''
  // const token = localStorage.getItem('token');
  if (to.name !== 'Login' && !token) {
    next('/login');
  } else {
    next();
  }
});
