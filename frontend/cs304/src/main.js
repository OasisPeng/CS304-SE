import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import axios from "axios";
Vue.prototype.$axios=axios;
Vue.prototype.$httpUrl='http://localhost:8090'
// Vue.prototype.$httpUrl='http://119.23.104.193:8090'
Vue.config.productionTip = false

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app')
