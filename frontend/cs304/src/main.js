import Vue from 'vue'
import App from './App.vue'
import router from './router';
import vuetify from './plugins/vuetify'
import axios from "axios";
import './permission.js'
Vue.prototype.$axios=axios;
Vue.prototype.$httpUrl='http://localhost:8090'
// Vue.prototype.$httpUrl='http://8.138.27.126:8090'
Vue.config.productionTip = false
localStorage.clear()
new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
