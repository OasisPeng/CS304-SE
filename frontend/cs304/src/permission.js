
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
